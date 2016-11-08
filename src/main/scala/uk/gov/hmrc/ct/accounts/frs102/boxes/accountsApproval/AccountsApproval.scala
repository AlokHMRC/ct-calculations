/*
 * Copyright 2016 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ct.accounts.frs102.boxes.accountsApproval

import uk.gov.hmrc.ct.accounts.frs102.retriever.{Frs102AccountsBoxRetriever, Frs10xDirectorsBoxRetriever, Frs10xFilingQuestionsBoxRetriever}
import uk.gov.hmrc.ct.box.retriever.FilingAttributesBoxValueRetriever
import uk.gov.hmrc.ct.box.{CtValidation, Input, ValidatableBox}

trait AccountsApproval extends Input with ValidatableBox[Frs102AccountsBoxRetriever with Frs10xDirectorsBoxRetriever with Frs10xFilingQuestionsBoxRetriever with FilingAttributesBoxValueRetriever]{

  val ac199A: List[AC199A]
  val ac8092: List[AC8092]
  val ac8091: AC8091
  val ac198A: AC198A

  def approvalEnabled(boxRetriever: Frs102AccountsBoxRetriever with Frs10xDirectorsBoxRetriever with Frs10xFilingQuestionsBoxRetriever with FilingAttributesBoxValueRetriever): Boolean

  private def filteredApprovers = ac199A.map(ac199A => ac199A.value)
  private def filteredOtherApprovers = ac8092.flatMap(ac8092 => ac8092.value)

  override def validate(boxRetriever: Frs102AccountsBoxRetriever with Frs10xDirectorsBoxRetriever with Frs10xFilingQuestionsBoxRetriever with FilingAttributesBoxValueRetriever): Set[CtValidation] = {
    collectWithBoxId(boxId) {
      failIf(approvalEnabled(boxRetriever)) {
        collectErrors(
          () => ac8091.validate(boxRetriever),
          () => ac198A.validate(boxRetriever),
          validateApproverRequired(boxRetriever),
          validateAtMost12Approvers(boxRetriever),
          validateAtMost12OtherApprovers(boxRetriever),
          validateApprovers(boxRetriever),
          validateOtherApprovers(boxRetriever)
        )
      }
    }
  }

  def anyValuesPopulated: Boolean = {
    ac199A.nonEmpty || ac8092.nonEmpty || ac8091.value.nonEmpty || ac198A.value.nonEmpty
  }

  private def validateApproverRequired(boxRetriever: Frs102AccountsBoxRetriever)(): Set[CtValidation] = {

    failIf(ac199A.isEmpty && filteredOtherApprovers.isEmpty) {
      Set(CtValidation(None, s"error.$boxId.atLeast1", None))
    }
  }

  private def validateAtMost12Approvers(boxRetriever: Frs102AccountsBoxRetriever)(): Set[CtValidation] = {

    failIf(filteredApprovers.length > 12) {
      Set(CtValidation(None, s"error.$boxId.approvers.atMost12", None))
    }
  }

  private def validateAtMost12OtherApprovers(boxRetriever: Frs102AccountsBoxRetriever)(): Set[CtValidation] = {

    failIf(filteredOtherApprovers.length > 12) {
      Set(CtValidation(None, s"error.$boxId.otherApprovers.atMost12", None))
    }
  }

  private def validateApprovers(boxRetriever: Frs102AccountsBoxRetriever)(): Set[CtValidation] = {

    val approversErrorList = for ((approver, index) <- ac199A.zipWithIndex) yield {
      val errors = approver.validate(boxRetriever)
      errors.map(error => error.copy(errorMessageKey = contextualiseListErrorKey(error.errorMessageKey, index.toString)))
    }
    approversErrorList.flatten.toSet
  }

  private def validateOtherApprovers(boxRetriever: Frs102AccountsBoxRetriever)(): Set[CtValidation] = {

    val otherApproversErrorList = for ((otherApprover, index) <- ac8092.zipWithIndex) yield {
      val errors = otherApprover.validate(boxRetriever)
      errors.map(error => error.copy(errorMessageKey = contextualiseListErrorKey(error.errorMessageKey, index.toString)))
    }
    otherApproversErrorList.flatten.toSet
  }

  private def contextualiseListErrorKey(errorKey: String, context: String): String = {
    val replaced = errorKey.replace("error", "error.simpleList")
    val splitKey = replaced.split('.')
    (splitKey.take(3) ++ Array(context) ++ splitKey.drop(3)).mkString(".")
  }

}
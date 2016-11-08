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

package uk.gov.hmrc.ct.accounts

import org.joda.time.LocalDate
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import org.scalatest.{Matchers, WordSpec}
import uk.gov.hmrc.ct.accounts.frs102.retriever.{Frs102AccountsBoxRetriever, _}
import uk.gov.hmrc.ct.accounts.frs105.retriever.Frs105AccountsBoxRetriever
import uk.gov.hmrc.ct.accounts.frs10x.retriever.{Frs10xFilingQuestionsBoxRetriever, Frs10xAccountsBoxRetriever}
import uk.gov.hmrc.ct.accounts.retriever.AccountsBoxRetriever
import uk.gov.hmrc.ct.box.retriever.FilingAttributesBoxValueRetriever
import uk.gov.hmrc.ct.box.{CtValidation, ValidatableBox}

sealed trait TestAccountsRetriever extends AccountsBoxRetriever with FilingAttributesBoxValueRetriever

sealed trait TestFrs10xAccountsRetriever extends Frs10xAccountsBoxRetriever with FilingAttributesBoxValueRetriever with Frs10xDirectorsBoxRetriever with Frs10xFilingQuestionsBoxRetriever

sealed trait TestFrs102AccountsRetriever extends Frs102AccountsBoxRetriever with FilingAttributesBoxValueRetriever with Frs10xDirectorsBoxRetriever with Frs10xFilingQuestionsBoxRetriever

sealed trait TestFrs105AccountsRetriever extends Frs105AccountsBoxRetriever with FilingAttributesBoxValueRetriever

sealed trait TestAbridgedAccountsRetriever extends AbridgedAccountsBoxRetriever with FilingAttributesBoxValueRetriever with Frs10xDirectorsBoxRetriever with Frs10xFilingQuestionsBoxRetriever

sealed trait TestFullAccountsRetriever extends FullAccountsBoxRetriever with FilingAttributesBoxValueRetriever with Frs10xDirectorsBoxRetriever with Frs10xFilingQuestionsBoxRetriever

trait MockAccountsRetriever extends MockitoSugar {
  val boxRetriever = mock[TestAccountsRetriever]
}

trait MockFrs10xAccountsRetriever extends MockitoSugar {
  val boxRetriever = mock[TestFrs10xAccountsRetriever]
}

trait MockFrs102AccountsRetriever extends MockitoSugar {
  val boxRetriever = mock[TestFrs102AccountsRetriever]
}

trait MockFrs105AccountsRetriever extends MockitoSugar {
  val boxRetriever = mock[TestFrs105AccountsRetriever](RETURNS_SMART_NULLS)
}

trait MockAbridgedAccountsRetriever extends MockitoSugar {
  val boxRetriever = mock[TestAbridgedAccountsRetriever]
}

trait MockFullAccountsRetriever extends MockitoSugar {
  val boxRetriever = mock[TestFullAccountsRetriever]
}

trait AccountsPreviousPeriodValidationFixture[T <: AccountsBoxRetriever] extends WordSpec with Matchers with MockitoSugar {

  def boxRetriever: T

  def testAccountsPreviousPoAValidation(boxId: String, builder: (Option[Int]) => ValidatableBox[T]): Unit = {

    s"$boxId" should {
      "pass validation when has valid value and AC205 is populated" in {
        when(boxRetriever.ac205()).thenReturn(AC205(Some(new LocalDate("2015-01-01"))))
        builder(Some(1)).validate(boxRetriever) shouldBe empty
      }
      "pass validation when empty and AC205 is populated" in {
        when(boxRetriever.ac205()).thenReturn(AC205(Some(new LocalDate("2015-01-01"))))
        builder(None).validate(boxRetriever) shouldBe empty
      }
      "pass validation when empty and AC205 is empty" in {
        when(boxRetriever.ac205()).thenReturn(AC205(None))
        builder(None).validate(boxRetriever) shouldBe empty
      }
      "fail validation when valid value and AC205 is empty" in {
        when(boxRetriever.ac205()).thenReturn(AC205(None))
        builder(Some(10)).validate(boxRetriever) shouldBe Set(CtValidation(Some(boxId), s"error.$boxId.cannot.exist"))
      }
    }
  }
}

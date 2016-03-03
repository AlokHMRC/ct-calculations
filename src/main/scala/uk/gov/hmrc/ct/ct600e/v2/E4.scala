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

package uk.gov.hmrc.ct.ct600e.v2

import uk.gov.hmrc.ct.box._
import uk.gov.hmrc.ct.ct600e.v2.retriever.CT600EBoxRetriever

case class E4(value: Option[Int]) extends CtBoxIdentifier("Amounts overclaimed for period") with CtOptionalInteger with Input with ValidatableBox[CT600EBoxRetriever] {


  override def validate(boxRetriever: CT600EBoxRetriever): Set[CtValidation] = {
    validateConditionalRequired(boxRetriever)
  }

  private def validateConditionalRequired(boxRetriever: CT600EBoxRetriever): Set[CtValidation] = {
    val e1 = boxRetriever.retrieveE1().value.getOrElse(0)
    val e2 = boxRetriever.retrieveE2().value.getOrElse(0)

    value match {
      case None if e2 < e1 => Set(CtValidation(Some("E4"), s"error.E4.conditionalRequired"))
      case _ => Set()
    }
  }
}


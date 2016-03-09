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

package uk.gov.hmrc.ct.ct600.v3

import uk.gov.hmrc.ct.box._
import uk.gov.hmrc.ct.ct600.v3.retriever.{RepaymentsBoxRetriever, CT600DeclarationBoxRetriever, CT600BoxRetriever}


case class B960_3(value: Option[String]) extends CtBoxIdentifier("Payee Address Line 3")
    with CtOptionalString with Input with ValidatableBox[RepaymentsBoxRetriever] {

  def validate(boxRetriever: RepaymentsBoxRetriever): Set[CtValidation] = {
    validateOptionalStringByLength("B960_3", this, 1, 28) ++
    validateOptionalStringByRegex("B960_3", this, validNonForeignMoreRestrictiveCharacters)
  }
}
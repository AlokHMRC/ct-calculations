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

package uk.gov.hmrc.ct.accounts.frs10x.abridged

import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import org.scalatest.{Matchers, WordSpec}
import uk.gov.hmrc.ct.accounts.frs10x.{AccountsFreeTextValidationFixture, MockAbridgedAccountsRetriever}
import uk.gov.hmrc.ct.box.ValidatableBox._

class AC322Spec extends WordSpec
  with MockitoSugar
  with Matchers
  with MockAbridgedAccountsRetriever
  with AccountsFreeTextValidationFixture {

  override def setUpMocks(): Unit = {
    when(boxRetriever.ac322()).thenReturn(AC322(Some("text")))
  }

  testAccountsCharacterLimitValidation("AC322", StandardCohoTextfieldLimit, AC322)
  testAccountsRegexValidation("AC322", AC322)

}

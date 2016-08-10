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

import uk.gov.hmrc.ct.accounts.frs10x.{AccountsMoneyValidationFixture, MockAbridgedAccountsRetriever}
import org.mockito.Mockito._
import uk.gov.hmrc.ct.box.CtValidation

class AC106Spec extends AccountsMoneyValidationFixture with MockAbridgedAccountsRetriever {

  override def setUpMocks() = {
    super.setUpMocks()
    when(boxRetriever.ac7300()).thenReturn(AC7300(Some(true)))
  }

  testAccountsMoneyValidationWithMinMaxIgnoringEmptyTest("AC106", 0, 99999, AC106.apply)

  "AC106" should {

    "validate with mandatory error when AC7300 is true" in {

      when(boxRetriever.ac7300()).thenReturn(AC7300(Some(true)))

      AC106(None).validate(boxRetriever) shouldBe Set(CtValidation(Some("AC106"), "error.AC106.required"))
    }

    "not validate with any errors when AC7300 is true and AC106 has a value" in {

      when(boxRetriever.ac7300()).thenReturn(AC7300(Some(true)))

      AC106(Some(10)).validate(boxRetriever) shouldBe empty
    }

    "not validate with any errors when AC7300 is false and AC106 has no value" in {

      when(boxRetriever.ac7300()).thenReturn(AC7300(Some(false)))

      AC106(None).validate(boxRetriever) shouldBe empty
    }

    "not validate with any errors when AC7300 is None and AC106 has no value" in {
      when(boxRetriever.ac7300()).thenReturn(AC7300(None))

      AC106(None).validate(boxRetriever) shouldBe empty
    }

    "validate with should not exist error when AC7300 is None and AC106 has a value" in {
      when(boxRetriever.ac7300()).thenReturn(AC7300(None))

      AC106(Some(100)).validate(boxRetriever) shouldBe Set(CtValidation(Some("AC106"), "error.AC106.cannot.exist"))
    }

    "validate with should not exist error when AC7300 is false and AC106 has a value" in {
      when(boxRetriever.ac7300()).thenReturn(AC7300(Some(false)))

      AC106(Some(100)).validate(boxRetriever) shouldBe Set(CtValidation(Some("AC106"), "error.AC106.cannot.exist"))
    }

  }

}

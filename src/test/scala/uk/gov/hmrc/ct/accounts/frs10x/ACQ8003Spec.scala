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

package uk.gov.hmrc.ct.accounts.frs10x

import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import org.scalatest.{Matchers, WordSpec}
import uk.gov.hmrc.ct.accounts.frs10x.retriever.Frs10xAccountsBoxRetriever
import uk.gov.hmrc.ct.box.CtValidation
import uk.gov.hmrc.ct.box.retriever.FilingAttributesBoxValueRetriever
import uk.gov.hmrc.ct.{CompaniesHouseFiling, HMRCFiling, MicroEntityFiling}

class ACQ8003Spec extends WordSpec with Matchers with MockitoSugar {

  trait  MockableFrs10xBoxretrieverWithFilingAttributes extends Frs10xAccountsBoxRetriever with FilingAttributesBoxValueRetriever
  val mockBoxRetriever = mock[MockableFrs10xBoxretrieverWithFilingAttributes]

  when(mockBoxRetriever.retrieveCompaniesHouseFiling()).thenReturn(CompaniesHouseFiling(true))
  when(mockBoxRetriever.retrieveHMRCFiling()).thenReturn(HMRCFiling(true))
  when(mockBoxRetriever.retrieveMicroEntityFiling()).thenReturn(MicroEntityFiling(true))
  when(mockBoxRetriever.retrieveAC8021()).thenReturn(AC8021(Some(true)))
  when(mockBoxRetriever.retrieveAC8023()).thenReturn(AC8023(Some(true)))

  "AC8033 should" should {

    "validate successfully when no errors present" in {

      val secretary = ACQ8003(Some(true))

      secretary.validate(mockBoxRetriever) shouldBe empty
    }

    "validate as mandatory" in {

      val secretary = ACQ8003(None)

      secretary.validate(mockBoxRetriever) shouldBe Set(CtValidation(Some("ACQ8003"), "error.ACQ8003.required", None))
    }

    "no validate if no directors report" in {

      when(mockBoxRetriever.retrieveAC8021()).thenReturn(AC8021(None))
      when(mockBoxRetriever.retrieveAC8023()).thenReturn(AC8023(Some(false)))

      val secretary = ACQ8003(None)

      secretary.validate(mockBoxRetriever) shouldBe empty
    }
  }
}

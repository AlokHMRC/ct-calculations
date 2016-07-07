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

package uk.gov.hmrc.ct.accounts.frs10x.abridged.calculations

import org.scalatest.{Matchers, WordSpec}
import uk.gov.hmrc.ct.accounts.frs10x.abridged._

class ProfitOrLossFinancialYearCalculatorSpec extends WordSpec with Matchers {

  "ProfitOrLossFinancialYearCalculator" should {
    "calculating AC36" when {
      "return zero if all inputs are empty" in new ProfitOrLossFinancialYearCalculator {
        calculateAC36(AC32(0), AC34(None)) shouldBe AC36(0)
      }

      "return zero if all inputs are zero" in new ProfitOrLossFinancialYearCalculator {
        calculateAC36(AC32(0), AC34(Some(0))) shouldBe AC36(0)
      }

      "return sum if all values positive" in new ProfitOrLossFinancialYearCalculator {
        calculateAC36(AC32(16), AC34(Some(18))) shouldBe AC36(-2)
      }

      "return sum if values positive and negative" in new ProfitOrLossFinancialYearCalculator {
        calculateAC36(AC32(16), AC34(Some(-18))) shouldBe AC36(34)
      }

      "return sum if all values negative" in new ProfitOrLossFinancialYearCalculator {
        calculateAC36(AC32(-16), AC34(Some(-18))) shouldBe AC36(2)
      }
    }
  }

  "ProfitOrLossFinancialYearCalculator" should {
    "calculating AC37" when {
      "return zero if all inputs are empty" in new ProfitOrLossFinancialYearCalculator {
        calculateAC37(AC33(0), AC35(None)) shouldBe AC37(0)
      }

      "return zero if all inputs are zero" in new ProfitOrLossFinancialYearCalculator {
        calculateAC37(AC33(0), AC35(Some(0))) shouldBe AC37(0)
      }

      "return sum if all values positive" in new ProfitOrLossFinancialYearCalculator {
        calculateAC37(AC33(16), AC35(Some(18))) shouldBe AC37(-2)
      }

      "return sum if values positive and negative" in new ProfitOrLossFinancialYearCalculator {
        calculateAC37(AC33(16), AC35(Some(-18))) shouldBe AC37(34)
      }

      "return sum if all values negative" in new ProfitOrLossFinancialYearCalculator {
        calculateAC37(AC33(-16), AC35(Some(-18))) shouldBe AC37(2)
      }
    }
  }
}

/*
 * Copyright 2017 HM Revenue & Customs
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

import org.scalatest.{Matchers, WordSpec}
import org.scalatest.mock.MockitoSugar
import uk.gov.hmrc.ct.computations.retriever.ComputationsBoxRetriever
import org.mockito.Mockito._
import uk.gov.hmrc.ct.computations.{CP283, CP997}

class B285Spec extends WordSpec with MockitoSugar with Matchers {

  "B285" should {
    "be zero when CP997 and CP283 are None" in {
      val retriever = mock[ComputationsBoxRetriever]
      when(retriever.cp283()).thenReturn(CP283(None))
      when(retriever.cp997()).thenReturn(CP997(None))
      B285.calculate(retriever) shouldBe B285(0)
    }
    "be equal to CP997 when CP283 is None" in {
      val retriever = mock[ComputationsBoxRetriever]
      when(retriever.cp283()).thenReturn(CP283(None))
      when(retriever.cp997()).thenReturn(CP997(Some(1)))
      B285.calculate(retriever) shouldBe B285(1)
    }
    "be equal to CP283 when CP997 is None" in {
      val retriever = mock[ComputationsBoxRetriever]
      when(retriever.cp283()).thenReturn(CP283(Some(1)))
      when(retriever.cp997()).thenReturn(CP997(None))
      B285.calculate(retriever) shouldBe B285(1)
    }
    "be equal to the sum of CP283 and CP997 when both are defined" in {
      val retriever = mock[ComputationsBoxRetriever]
      when(retriever.cp283()).thenReturn(CP283(Some(1)))
      when(retriever.cp997()).thenReturn(CP997(Some(1)))
      B285.calculate(retriever) shouldBe B285(2)
    }
  }
}

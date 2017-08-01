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

import uk.gov.hmrc.ct.box.{Calculated, CtBoxIdentifier, CtInteger, CtTypeConverters}
import uk.gov.hmrc.ct.computations.retriever.ComputationsBoxRetriever

case class B285(value: Int) extends CtBoxIdentifier(name = "Trading losses carried forward and claimed against total profits") with CtInteger

object B285 extends Calculated[B285, ComputationsBoxRetriever] with CtTypeConverters {
  override def calculate(boxRetriever: ComputationsBoxRetriever): B285 = {
    B285(boxRetriever.cp283() + boxRetriever.cp997())
  }
}

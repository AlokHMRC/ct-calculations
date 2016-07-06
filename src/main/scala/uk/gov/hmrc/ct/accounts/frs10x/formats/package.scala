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

import play.api.libs.json.Format
import uk.gov.hmrc.ct.box.formats.{OptionalBooleanFormat, OptionalStringFormat}

package object formats {
  implicit val ac8023Format: Format[AC8023] = new OptionalBooleanFormat[AC8023](AC8023.apply)
  implicit val ac8051Format: Format[AC8051] = new OptionalStringFormat[AC8051](AC8051.apply)
  implicit val ac8052Format: Format[AC8052] = new OptionalStringFormat[AC8052](AC8052.apply)
  implicit val ac8053Format: Format[AC8053] = new OptionalStringFormat[AC8053](AC8053.apply)
  implicit val ac8054Format: Format[AC8054] = new OptionalStringFormat[AC8054](AC8054.apply)
  implicit val acq8161Format: Format[ACQ8161] = new OptionalBooleanFormat[ACQ8161](ACQ8161.apply)
}

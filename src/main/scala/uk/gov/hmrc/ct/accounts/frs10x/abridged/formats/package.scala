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

import uk.gov.hmrc.ct.box.formats.{OptionalBooleanFormat, OptionalIntegerFormat, OptionalStringFormat}

package object formats {
  implicit val ac16Format = new OptionalIntegerFormat[AC16](AC16.apply)
  implicit val ac17Format = new OptionalIntegerFormat[AC17](AC17.apply)
  implicit val ac18Format = new OptionalIntegerFormat[AC18](AC18.apply)
  implicit val ac19Format = new OptionalIntegerFormat[AC19](AC19.apply)
  implicit val ac20Format = new OptionalIntegerFormat[AC20](AC20.apply)
  implicit val ac21Format = new OptionalIntegerFormat[AC21](AC21.apply)
  implicit val ac28Format = new OptionalIntegerFormat[AC28](AC28.apply)
  implicit val ac29Format = new OptionalIntegerFormat[AC29](AC29.apply)
  implicit val ac30Format = new OptionalIntegerFormat[AC30](AC30.apply)
  implicit val ac31Format = new OptionalIntegerFormat[AC31](AC31.apply)
  implicit val ac34Format = new OptionalIntegerFormat[AC34](AC34.apply)
  implicit val ac35Format = new OptionalIntegerFormat[AC35](AC35.apply)
  implicit val ac42Format = new OptionalIntegerFormat[AC42](AC42.apply)
  implicit val ac43Format = new OptionalIntegerFormat[AC43](AC43.apply)
  implicit val ac44Format = new OptionalIntegerFormat[AC44](AC44.apply)
  implicit val ac45Format = new OptionalIntegerFormat[AC45](AC45.apply)
  implicit val ac50Format = new OptionalIntegerFormat[AC50](AC50.apply)
  implicit val ac51Format = new OptionalIntegerFormat[AC51](AC51.apply)
  implicit val ac52Format = new OptionalIntegerFormat[AC52](AC52.apply)
  implicit val ac53Format = new OptionalIntegerFormat[AC53](AC53.apply)
  implicit val ac54Format = new OptionalIntegerFormat[AC54](AC54.apply)
  implicit val ac55Format = new OptionalIntegerFormat[AC55](AC55.apply)
  implicit val ac58Format = new OptionalIntegerFormat[AC58](AC58.apply)
  implicit val ac59Format = new OptionalIntegerFormat[AC59](AC59.apply)
  implicit val ac64Format = new OptionalIntegerFormat[AC64](AC64.apply)
  implicit val ac65Format = new OptionalIntegerFormat[AC65](AC65.apply)
  implicit val ac66Format = new OptionalIntegerFormat[AC66](AC66.apply)
  implicit val ac67Format = new OptionalIntegerFormat[AC67](AC67.apply)
  implicit val ac70Format = new OptionalIntegerFormat[AC70](AC70.apply)
  implicit val ac71Format = new OptionalIntegerFormat[AC71](AC71.apply)
  implicit val ac74Format = new OptionalIntegerFormat[AC74](AC74.apply)
  implicit val ac75Format = new OptionalIntegerFormat[AC75](AC75.apply)
  implicit val ac76Format = new OptionalIntegerFormat[AC76](AC76.apply)
  implicit val ac77Format = new OptionalIntegerFormat[AC77](AC77.apply)
  implicit val ac1076Format = new OptionalIntegerFormat[AC1076](AC1076.apply)
  implicit val ac1077Format = new OptionalIntegerFormat[AC1077](AC1077.apply)
  implicit val ac1178Format = new OptionalIntegerFormat[AC1178](AC1178.apply)
  implicit val ac1179Format = new OptionalIntegerFormat[AC1179](AC1179.apply)
  implicit val ac5032Format = new OptionalStringFormat[AC5032](AC5032.apply)
  implicit val ac5052AFormat = new OptionalIntegerFormat[AC5052A](AC5052A.apply)
  implicit val ac5052BFormat = new OptionalStringFormat[AC5052B](AC5052B.apply)

  implicit val ac7100Format = new OptionalBooleanFormat(AC7100.apply)
  implicit val ac7200Format = new OptionalBooleanFormat(AC7200.apply)
  implicit val ac7300Format = new OptionalBooleanFormat(AC7300.apply)
  implicit val ac7400Format = new OptionalBooleanFormat(AC7400.apply)
  implicit val ac7500Format = new OptionalBooleanFormat(AC7500.apply)
  implicit val ac7600Format = new OptionalBooleanFormat(AC7600.apply)
  implicit val ac7800Format = new OptionalBooleanFormat(AC7800.apply)
  implicit val ac7900Format = new OptionalBooleanFormat(AC7900.apply)
}

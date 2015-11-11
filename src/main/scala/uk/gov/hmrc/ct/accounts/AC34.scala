package uk.gov.hmrc.ct.accounts

import uk.gov.hmrc.ct.box.{CtBoxIdentifier, CtOptionalInteger, Input, MustBeNoneOrZeroOrPositive}

case class AC34(value: Option[Int]) extends CtBoxIdentifier(name = "Current Tax on profit or loss")
                                    with CtOptionalInteger with MustBeNoneOrZeroOrPositive with Input

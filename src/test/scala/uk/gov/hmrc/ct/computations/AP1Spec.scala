package uk.gov.hmrc.ct.computations

import org.scalatest.mock.MockitoSugar
import org.scalatest.{Matchers, WordSpec}
import uk.gov.hmrc.ct.computations.retriever.ComputationsBoxRetriever
import org.mockito.Mockito.when
import uk.gov.hmrc.ct.accounts.retriever.AccountsBoxRetriever
import uk.gov.hmrc.ct.box.CtValidation
import uk.gov.hmrc.ct.box.retriever.FilingAttributesBoxValueRetriever


class AP1Spec extends WordSpec with MockitoSugar with Matchers  {
  trait ComputationsWithAccountsBoxRetriever extends ComputationsBoxRetriever with AccountsBoxRetriever with FilingAttributesBoxValueRetriever
  val boxRetriever = mock[ComputationsWithAccountsBoxRetriever]

  "AP1" when {
    "empty" should {
      "not fail if AP2 are empty" in {
        when(boxRetriever.ap2()).thenReturn(AP2(None))

        AP1(None).validate(boxRetriever) shouldBe Set.empty
      }

      "fail if AP2 is defined" in {
        when(boxRetriever.ap2()).thenReturn(AP2(Some(1)))

        AP1(None).validate(boxRetriever) shouldBe Set(CtValidation(Some("AP1"), "error.AP1.required"))
      }
    }
  }
}



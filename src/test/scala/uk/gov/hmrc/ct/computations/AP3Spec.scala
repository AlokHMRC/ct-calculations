package uk.gov.hmrc.ct.computations

import org.mockito.Mockito.when
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.mock.MockitoSugar
import uk.gov.hmrc.ct.accounts.retriever.AccountsBoxRetriever
import uk.gov.hmrc.ct.box.CtValidation
import uk.gov.hmrc.ct.box.retriever.FilingAttributesBoxValueRetriever
import uk.gov.hmrc.ct.computations.retriever.ComputationsBoxRetriever

class AP3Spec extends WordSpec with MockitoSugar with Matchers  {
  trait ComputationsWithAccountsBoxRetriever extends ComputationsBoxRetriever with AccountsBoxRetriever with FilingAttributesBoxValueRetriever
  val boxRetriever = mock[ComputationsWithAccountsBoxRetriever]

  "AP3" when {
    "empty" should {
      "not fail if AP2 are empty" in {
        when(boxRetriever.ap2()).thenReturn(AP2(None))

        AP3(None).validate(boxRetriever) shouldBe Set.empty
      }

      "fail if AP2 is defined" in {
        when(boxRetriever.ap2()).thenReturn(AP2(Some(1)))

        AP3(None).validate(boxRetriever) shouldBe Set(CtValidation(Some("AP3"), "error.AP3.required"))
      }
    }
  }
}

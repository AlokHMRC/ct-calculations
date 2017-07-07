package uk.gov.hmrc.ct.computations

import org.mockito.Mockito.when
import org.scalatest.{Matchers, WordSpec}
import org.scalatest.mock.MockitoSugar
import uk.gov.hmrc.ct.accounts.AC12
import uk.gov.hmrc.ct.accounts.retriever.AccountsBoxRetriever
import uk.gov.hmrc.ct.box.CtValidation
import uk.gov.hmrc.ct.box.retriever.FilingAttributesBoxValueRetriever
import uk.gov.hmrc.ct.computations.retriever.ComputationsBoxRetriever

class AP2Spec extends WordSpec with MockitoSugar with Matchers  {
  trait ComputationsWithAccountsBoxRetriever extends ComputationsBoxRetriever with AccountsBoxRetriever with FilingAttributesBoxValueRetriever
  val boxRetriever = mock[ComputationsWithAccountsBoxRetriever]

  "AP2" when {
    "empty" should {
      "fail as mandatory" in {
        when(boxRetriever.ap1()).thenReturn(AP1(Some(1)))
        when(boxRetriever.ap3()).thenReturn(AP3(Some(1)))
        when(boxRetriever.ac12()).thenReturn(AC12(Some(3)))

        AP2(None).validate(boxRetriever) shouldBe Set(CtValidation(Some("AP2"), "error.AP2.required"))
      }
    }

    "defined" should {
      "fail when its sum with AP1 and AP3 is less than AC12" in {
        when(boxRetriever.ap1()).thenReturn(AP1(Some(1)))
        when(boxRetriever.ap3()).thenReturn(AP3(Some(1)))
        when(boxRetriever.ac12()).thenReturn(AC12(Some(4)))

        AP2(Some(1)).validate(boxRetriever) shouldBe Set(CtValidation(None, "error.AP2.sum.notEqualTo.AC12"))
      }

      "fail if its sum with AP1 and AP3 is more than AC12" in {
        when(boxRetriever.ap1()).thenReturn(AP1(Some(2)))
        when(boxRetriever.ap3()).thenReturn(AP3(Some(2)))
        when(boxRetriever.ac12()).thenReturn(AC12(Some(4)))

        AP2(Some(1)).validate(boxRetriever) shouldBe Set(CtValidation(None, "error.AP2.sum.notEqualTo.AC12"))
      }

      "not fail if its sum with AP1 and AP3 is equal to AC12" in {
        when(boxRetriever.ap1()).thenReturn(AP1(Some(1)))
        when(boxRetriever.ap3()).thenReturn(AP3(Some(1)))
        when(boxRetriever.ac12()).thenReturn(AC12(Some(3)))

        AP2(Some(1)).validate(boxRetriever) shouldBe Set.empty
      }
    }
  }
}

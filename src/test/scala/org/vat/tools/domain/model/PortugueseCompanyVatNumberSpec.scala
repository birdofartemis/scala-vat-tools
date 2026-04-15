package org.vat.tools.domain.model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.vat.tools.domain.model.entities.PortugueseCompanyVatNumber
import org.vat.tools.domain.model.value.objects.{
  VatNumberValidationFailed,
  VatNumberValidationSuccess
}

class PortugueseCompanyVatNumberSpec extends AnyFlatSpec with Matchers {

  "PortugueseCompanyVatNumber.apply" should
    "remove non-digit characters from the input" in {

      val vat =
        PortugueseCompanyVatNumber("PT 123 456 789")

      vat.vatNumber shouldBe "123456789"
    }

  it should "create a valid VAT number when all rules pass" in {

    // Example valid Portuguese individual VAT number
    val vatNumber = "523456786"
    val vat =
      PortugueseCompanyVatNumber(vatNumber)

    val result = vat.isValid

    result shouldBe Right(VatNumberValidationSuccess(vatNumber))
  }

  "isValid" should "fail when VAT number size is invalid" in {

    val vat =
      PortugueseCompanyVatNumber("52345678") // 8 digits

    val result = vat.isValid

    result.isLeft shouldBe true

    result shouldBe Left(
      VatNumberValidationFailed(
        label = "size-rule",
        error =
          "The provided vat number: [52345678] has a length of: [8] while the authorized size is: [9]"
      )
    )
  }

  it should "fail when identification prefix is invalid" in {

    val vat =
      PortugueseCompanyVatNumber("199456789")

    val result = vat.isValid

    result shouldBe Left(
      VatNumberValidationFailed(
        label = "identification-rule",
        error =
          "The provided vat number: [199456789] with prefix: [1] is invalid for the provided list: [8, 9, 5, 6, 7]"
      )
    )
  }

  it should "fail when check digit is invalid" in {

    // Same prefix but wrong check digit
    val vat =
      PortugueseCompanyVatNumber("923456780")

    val result = vat.isValid

    result shouldBe Left(
      VatNumberValidationFailed(
        label = "check-digit-rule",
        error =
          "The provided vat number: [923456780] with last digit: [0] is not valid"
      )
    )
  }

  "prefix" should "be derived from the first two digits" in {
    val vat = PortugueseCompanyVatNumber("551234567")
    vat.prefix shouldBe "5"
  }

  "identifications" should "contain valid individual prefixes" in {
    val vat = PortugueseCompanyVatNumber("523456789")

    vat.identifications should contain allOf (
      "8",
      "9",
      "5",
      "6",
      "7"
    )
  }
}

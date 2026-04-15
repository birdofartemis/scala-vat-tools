package org.vat.tools.domain.model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.vat.tools.domain.model.entities.PortugueseIndividualVatNumber
import org.vat.tools.domain.model.value.objects.{
  VatNumberValidationFailed,
  VatNumberValidationSuccess
}

class PortugueseIndividualVatNumberSpec extends AnyFlatSpec with Matchers {

  "PortugueseIndividualVatNumber.apply" should
    "remove non-digit characters from the input" in {

      val vat =
        PortugueseIndividualVatNumber("PT 123 456 789")

      vat.vatNumber shouldBe "123456789"
    }

  it should "create a valid VAT number when all rules pass" in {

    // Example valid Portuguese individual VAT number
    val vatNumber = "123456789"
    val vat =
      PortugueseIndividualVatNumber(vatNumber)

    val result = vat.isValid

    result shouldBe Right(VatNumberValidationSuccess(vatNumber))
  }

  "isValid" should "fail when VAT number size is invalid" in {

    val vat =
      PortugueseIndividualVatNumber("12345678") // 8 digits

    val result = vat.isValid

    result.isLeft shouldBe true

    result shouldBe Left(
      VatNumberValidationFailed(
        label = "size-rule",
        error =
          "The provided vat number: [12345678] has a length of: [8] while the authorized size is: [9]"
      )
    )
  }

  it should "fail when identification prefix is invalid" in {

    val vat =
      PortugueseIndividualVatNumber("999456789")

    val result = vat.isValid

    result shouldBe Left(
      VatNumberValidationFailed(
        label = "identification-rule",
        error =
          "The provided vat number: [999456789] with prefix: [99] is invalid for the provided list: [1, 2, 3, 45]"
      )
    )
  }

  it should "fail when check digit is invalid" in {

    // Same prefix but wrong check digit
    val vat =
      PortugueseIndividualVatNumber("123456780")

    val result = vat.isValid

    result shouldBe Left(
      VatNumberValidationFailed(
        label = "check-digit-rule",
        error =
          "The provided vat number: [123456780] with last digit: [0] is not valid"
      )
    )
  }

  "prefix" should "be derived from the first two digits" in {
    val vat = PortugueseIndividualVatNumber("451234567")
    vat.prefix shouldBe "45"
  }

  "identifications" should "contain valid individual prefixes" in {
    val vat = PortugueseIndividualVatNumber("123456789")

    vat.identifications should contain allOf (
      "1",
      "2",
      "3",
      "45"
    )
  }
}

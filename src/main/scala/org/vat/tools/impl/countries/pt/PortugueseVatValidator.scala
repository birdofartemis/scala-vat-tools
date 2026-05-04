package org.vat.tools.impl.countries.pt

import org.vat.tools.domain.error.VatError
import org.vat.tools.domain.model.VatNumber
import org.vat.tools.domain.ports.VatValidator

object PortugueseVatValidator extends VatValidator {

  override def validate(vat: VatNumber): Either[VatError, Unit] = {
    val value = vat.value

    if (!value.matches("\\d{9}"))
      Left(VatError.InvalidFormat)
    else if (!isValidChecksum(value))
      Left(VatError.InvalidChecksum)
    else
      Right(())
  }

  private def isValidChecksum(vat: String): Boolean = {
    val digits     = vat.map(_.asDigit)
    val checkDigit = digits.last

    val sum = digits
      .take(8)
      .zipWithIndex
      .map { case (digit, idx) => digit * (9 - idx) }
      .sum

    val mod11    = sum % 11
    val expected = if (mod11 < 2) 0 else 11 - mod11

    checkDigit == expected
  }
}

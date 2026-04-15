package org.vat.tools.domain.model.validators

import org.vat.tools.domain.model.validations.{
  CheckDigitRuleValidation,
  IdentificationRuleValidation,
  SizeRuleValidation
}
import org.vat.tools.domain.model.value.objects.Rule

trait PortugueseVatNumberValidator
    extends CheckDigitRuleValidation
    with IdentificationRuleValidation
    with SizeRuleValidation {
  override def sizeRule(vatNumber: String, size: Int): Rule =
    Rule(
      label = "size-rule",
      condition = _ => vatNumber.length == size,
      vatNumber = vatNumber,
      errorMessage =
        s"The provided vat number: [$vatNumber] has a length of: [${vatNumber.length}] while the authorized size is: [$size]"
    )

  override def checkDigitRule(vatNumber: String): Rule = {
    val digits = vatNumber.map(_.asDigit)

    val weightSum =
      digits
        .take(8) // fist 8 digits
        .zip(9 to 2 by -1)
        .map { case (digit, weight) => digit * weight }
        .sum

    val remainder = weightSum % 11

    val expectedDigit =
      if (remainder < 2) 0 else 11 - remainder

    Rule(
      label = "check-digit-rule",
      condition = _ => digits.last == expectedDigit,
      vatNumber = vatNumber,
      errorMessage =
        s"The provided vat number: [$vatNumber] with last digit: [${digits.last}] is not valid"
    )
  }
}

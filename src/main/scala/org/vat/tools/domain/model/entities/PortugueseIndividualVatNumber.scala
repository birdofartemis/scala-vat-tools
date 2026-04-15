package org.vat.tools.domain.model.entities

import org.vat.tools.domain.model.validations.{
  CheckDigitRuleValidation,
  IdentificationRuleValidation,
  SizeRuleValidation
}
import org.vat.tools.domain.model.value.objects.{
  Rule,
  VatNumberValidationFailed,
  VatNumberValidationSuccess
}

final case class PortugueseIndividualVatNumber private (vatNumber: String)
    extends PortugueseVatNumber(vatNumber)
    with CheckDigitRuleValidation
    with IdentificationRuleValidation
    with SizeRuleValidation {

  /** Individual (Pessoa Singular)
    *
    * 1 — Individual resident taxpayer 2 — Self-employed individual /
    * entrepreneur 3 — Individual non-resident 45 — Non-resident individual
    * without permanent establishment
    */
  override val identifications: Set[String] = Set("1", "2", "3", "45")
  override val prefix: String               = vatNumber.take(2)

  override def isValid
      : Either[VatNumberValidationFailed, VatNumberValidationSuccess] =
    Rule.evaluateRules(
      vatNumber = vatNumber,
      rules = sizeRule(
        vatNumber = vatNumber,
        size = PortugueseVatNumber.VAT_NUMBER_SIZE
      ),
      identificationRule(vatNumber = vatNumber),
      checkDigitRule(vatNumber = vatNumber)
    )
}

object PortugueseIndividualVatNumber {
  def apply(vatNumber: String): PortugueseIndividualVatNumber =
    new PortugueseIndividualVatNumber(vatNumber.filter(_.isDigit))
}

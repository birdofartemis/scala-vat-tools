package org.vat.tools.domain.model.entities

import org.vat.tools.domain.model.validations.{CheckDigitRuleValidation, IdentificationRuleValidation, SizeRuleValidation}
import org.vat.tools.domain.model.value.objects.{Rule, VatNumberValidationFailed, VatNumberValidationSuccess}

final case class PortugueseCompanyVatNumber private (vatNumber: String)
    extends PortugueseVatNumber(vatNumber)
    with CheckDigitRuleValidation
    with IdentificationRuleValidation
    with SizeRuleValidation {

  /**
   * Legal Entity (Pessoa Coletiva)
   *
   * 5 — Private companies (e.g., Lda, SA)
   * 6 — Public administration entities
   * 7 — Other legal persons
   * 8 — Non-resident legal entities
   * 9 — Irregular or temporary entities
   */
  override val identifications: Set[String] = Set("5", "6", "7", "8", "9")

  override val prefix: String               = vatNumber.take(1)

  override def isValid
      : Either[VatNumberValidationFailed, VatNumberValidationSuccess] =
    Rule.evaluateRules(
      vatNumber = vatNumber,
      rules = List(
        sizeRule(
          vatNumber = vatNumber,
          size = PortugueseVatNumber.VAT_NUMBER_SIZE
        ),
        identificationRule(vatNumber = vatNumber),
        checkDigitRule(vatNumber = vatNumber)
      )
    )
}

object PortugueseCompanyVatNumber {

  def apply(vatNumber: String): PortugueseCompanyVatNumber =
    new PortugueseCompanyVatNumber(vatNumber.filter(_.isDigit))
}

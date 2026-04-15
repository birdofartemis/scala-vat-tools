package org.vat.tools.domain.model.value.objects

case class Rule(
    label: String,
    condition: Unit => Boolean,
    vatNumber: String,
    errorMessage: String
) {
  def evaluate: Either[VatNumberValidationFailed, VatNumberValidationSuccess] =
    if (condition())
      Right(VatNumberValidationSuccess(vatNumber))
    else Left(VatNumberValidationFailed(label, errorMessage))
}

object Rule {
  private final val CHECK_DIGIT_LABEL: String    = "check-digit-rule"
  private final val SIZE_LABEL: String           = "size-rule"
  private final val IDENTIFICATION_LABEL: String = "identification-rule"

  def evaluateRules(
      vatNumber: String,
      rules: Rule*
  ): Either[VatNumberValidationFailed, VatNumberValidationSuccess] =
    rules
      .collectFirst {
        case rule if rule.evaluate.isLeft => rule.evaluate
      }
      .getOrElse(Right(VatNumberValidationSuccess(vatNumber)))
}

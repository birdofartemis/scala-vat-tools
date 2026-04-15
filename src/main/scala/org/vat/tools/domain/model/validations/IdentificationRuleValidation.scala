package org.vat.tools.domain.model.validations

import org.vat.tools.domain.model.value.objects.Rule

private[model] trait IdentificationRuleValidation {

  def identificationRule(
      vatNumber: String,
      identifications: Set[String],
      prefix: String
  ): Rule =
    Rule(
      label = "identification-rule",
      condition = _ => identifications.exists(prefix.contains),
      vatNumber = vatNumber,
      errorMessage =
        s"The provided vat number: [$vatNumber] with prefix: [$prefix] is invalid for the provided list: [${identifications.mkString(", ")}]"
    )
}

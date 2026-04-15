package org.vat.tools.domain.model.validations

import org.vat.tools.domain.model.value.objects.Rule

private[model] trait SizeRuleValidation {
  def sizeRule(vatNumber: String, size: Int): Rule =
    Rule(
      label = "size-rule",
      condition = _ => vatNumber.length == size,
      vatNumber = vatNumber,
      errorMessage =
        s"The provided vat number: [$vatNumber] has a length of: [${vatNumber.length}] while the authorized size is: [$size]"
    )
}

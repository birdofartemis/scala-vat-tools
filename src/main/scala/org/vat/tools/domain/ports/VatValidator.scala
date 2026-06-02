package org.vat.tools.domain.ports

import org.vat.tools.domain.error.VatError
import org.vat.tools.domain.model.VatNumber

//todo change the result type on the right side
trait VatValidator {
  def validate(vat: VatNumber): Either[VatError, Unit]
}

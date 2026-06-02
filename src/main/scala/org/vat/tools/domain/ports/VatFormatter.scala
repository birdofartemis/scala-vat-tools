package org.vat.tools.domain.ports

import org.vat.tools.domain.model.VatNumber

//todo add clean method
trait VatFormatter {
  def format(vat: VatNumber): String
}

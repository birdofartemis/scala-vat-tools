package org.vat.tools.domain.ports

import org.vat.tools.domain.model.VatNumber

trait VatFormatter {
  def format(vat: VatNumber): String
}

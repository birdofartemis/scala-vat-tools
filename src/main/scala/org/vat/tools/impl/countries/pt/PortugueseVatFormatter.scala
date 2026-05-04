package org.vat.tools.impl.countries.pt

import org.vat.tools.domain.model.VatNumber
import org.vat.tools.domain.ports.VatFormatter

object PortugueseVatFormatter extends VatFormatter {
  override def format(vat: VatNumber): String =
    vat.value.grouped(3).mkString(" ")
}

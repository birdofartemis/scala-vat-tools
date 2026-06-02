package org.vat.tools.impl.registry

import org.vat.tools.application.VatComponent
import org.vat.tools.domain.model.CountryCode
import org.vat.tools.impl.countries.pt.{
  PortugueseVatFormatter,
  PortugueseVatValidator
}

object VatRulesRegistry {

  def forCountry(country: CountryCode): VatComponent =
    country match {
      case CountryCode.PT =>
        VatComponent(
          validator = PortugueseVatValidator,
          formatter = PortugueseVatFormatter
        )
    }
}

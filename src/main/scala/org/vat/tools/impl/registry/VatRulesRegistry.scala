package org.vat.tools.impl.registry

import org.vat.tools.application.VatRules
import org.vat.tools.domain.model.CountryCode
import org.vat.tools.impl.countries.pt.{
  PortugueseVatFormatter,
  PortugueseVatValidator
}

object VatRulesRegistry {

  def forCountry(country: CountryCode): VatRules =
    country match {
      case CountryCode.PT =>
        VatRules(
          validator = PortugueseVatValidator,
          formatter = PortugueseVatFormatter
        )
    }
}

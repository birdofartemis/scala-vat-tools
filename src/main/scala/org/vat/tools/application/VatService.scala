package org.vat.tools.application

import org.vat.tools.domain.error.VatError
import org.vat.tools.domain.model.VatNumber
import org.vat.tools.impl.registry.VatRulesRegistry

object VatService {

  def process(vat: VatNumber): Either[VatError, String] = {
    val rules = VatRulesRegistry.forCountry(vat.country)

    for {
      _ <- rules.validator.validate(vat)
    } yield rules.formatter.format(vat)
  }
}
package org.vat.tools.application

import org.vat.tools.domain.error.VatError
import org.vat.tools.domain.model.VatNumber
import org.vat.tools.impl.registry.VatRulesRegistry

object VatService {

  //todo this needs to be updated
  def process(vat: VatNumber): Either[VatError, String] = {
    val resource = VatRulesRegistry.forCountry(vat.country)

    for {
      _ <- resource.validator.validate(vat)
    } yield resource.formatter.format(vat)
  }
}
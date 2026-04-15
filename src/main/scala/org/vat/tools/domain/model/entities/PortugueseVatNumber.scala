package org.vat.tools.domain.model.entities

import org.vat.tools.domain.model.value.objects.{CountryCode, PT, VatNumberValidationFailed, VatNumberValidationSuccess}

protected abstract class PortugueseVatNumber protected (vatNumber: String) {

  private val companyCode: CountryCode = PT

  def isValid: Either[VatNumberValidationFailed, VatNumberValidationSuccess]

  def format: String = companyCode.iso2 + vatNumber
}

object PortugueseVatNumber {
  final val VAT_NUMBER_SIZE: Int = 9
}

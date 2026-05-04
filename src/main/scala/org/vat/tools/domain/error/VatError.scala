package org.vat.tools.domain.error

trait VatError {
  def message: String
}

object VatError {
  final case object InvalidFormat extends VatError {
    val message = "Invalid VAT format"
  }

  final case object InvalidChecksum extends VatError {
    val message = "Invalid VAT checksum"
  }
}

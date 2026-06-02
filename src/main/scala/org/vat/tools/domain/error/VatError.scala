package org.vat.tools.domain.error

trait VatError {
  def message: String
}

//todo missing case for size and generic messages
object VatError {
  final case object InvalidFormat extends VatError {
    val message = "Invalid VAT format"
  }

  final case object InvalidChecksum extends VatError {
    val message = "Invalid VAT checksum"
  }

  final case object InvalidSize extends VatError {
    val message: String = ???
  }

  final case object InvalidIdentification extends VatError {
    val message: String = ???
  }
}

package org.vat.tools.domain.model

sealed trait CountryCode

object CountryCode {
  case object PT extends CountryCode
}

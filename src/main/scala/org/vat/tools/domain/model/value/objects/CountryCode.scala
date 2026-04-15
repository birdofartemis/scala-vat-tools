package org.vat.tools.domain.model.value.objects

sealed trait CountryCode {
  def iso2: String
  def iso3: String
  def value: String
}

case object PT extends CountryCode {
  val iso2: String = "PT"
  val iso3: String = "PRT"
  val value: String = "Portugal"
}

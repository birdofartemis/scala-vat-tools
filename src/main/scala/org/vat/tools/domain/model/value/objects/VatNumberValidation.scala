package org.vat.tools.domain.model.value.objects

trait VatNumberValidation {
  def toBoolean: Boolean
}

case class VatNumberValidationFailed(label: String, error: String)
    extends VatNumberValidation {
  override def toBoolean: Boolean = false
}
case class VatNumberValidationSuccess(vatNumber: String)
    extends VatNumberValidation {
  override def toBoolean: Boolean = true
}

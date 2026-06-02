package org.vat.tools.application

import org.vat.tools.domain.ports.{VatFormatter, VatValidator}

final case class VatComponent(
    validator: VatValidator,
    formatter: VatFormatter
)

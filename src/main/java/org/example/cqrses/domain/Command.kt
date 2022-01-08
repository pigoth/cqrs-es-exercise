package org.example.cqrses.domain

import java.util.*

data class AcquireCustomer(
  val id: UUID,
  val name: String,
  val surname: String,
  val fiscalCode: String,
  val address: String
)

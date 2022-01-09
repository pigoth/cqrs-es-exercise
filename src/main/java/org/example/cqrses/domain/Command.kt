package org.example.cqrses.domain

import java.util.*

interface Command

data class AcquireCustomer(
  val id: UUID,
  val name: String,
  val surname: String,
  val fiscalCode: String,
  val address: String
) : Command

data class ModifyCustomerPersonalData(
  val customerId: UUID,
  val address: String
) : Command

package org.example.cqrses.domain

import java.util.*

interface Event

data class CustomerAcquired(
  val id: UUID,
  val name: String,
  val surname: String,
  val fiscalCode: String,
  val address: String
) : Event

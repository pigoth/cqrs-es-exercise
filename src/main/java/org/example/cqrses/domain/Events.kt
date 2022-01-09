package org.example.cqrses.domain

import java.time.LocalDateTime
import java.util.*

interface Event {
  val id: UUID
  val at: LocalDateTime
}

data class CustomerAcquired(
  override val id: UUID,
  val name: String,
  val surname: String,
  val fiscalCode: String,
  val address: String,
  override val at: LocalDateTime
) : Event

data class CustomerPersonalDataModified(
  override val id: UUID,
  val address: String,
  override val at: LocalDateTime
) : Event

package org.example.cqrses.domain

import java.time.LocalDateTime
import java.util.*

class Customer {
  private val changes = mutableListOf<Event>()

  private lateinit var id: UUID
  private lateinit var address: String

  fun changes() = changes.toList()

  fun acquire(id: UUID, name: String, surname: String, fiscalCode: String, address: String, at: LocalDateTime) {
    changes.add(CustomerAcquired(id, name, surname, fiscalCode, address, at))
  }

  fun modifyPersonalData(address: String, at: LocalDateTime) {
    changes.add(CustomerPersonalDataModified(id, address, at))
  }

  fun hydrate(events: List<Event>): Customer {
    events.forEach { event ->
      if (event is CustomerAcquired) {
        id = event.id
        address = event.address
      }
    }

    return this
  }

}

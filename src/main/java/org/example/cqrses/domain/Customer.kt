package org.example.cqrses.domain

import java.util.*

class Customer {
  private val changes = mutableListOf<Event>()

  private lateinit var id: UUID
  private lateinit var address: String

  fun changes() = changes.toList()

  fun acquire(id: UUID, name: String, surname: String, fiscalCode: String, address: String) {
    changes.add(CustomerAcquired(id, name, surname, fiscalCode, address))
  }

  fun modifyPersonalData(address: String) {
    changes.add(CustomerPersonalDataModified(id, address))
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

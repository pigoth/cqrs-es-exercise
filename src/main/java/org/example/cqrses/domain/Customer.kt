package org.example.cqrses.domain

import java.util.*

class Customer {
  private val changes = mutableListOf<Event>()

  fun acquire(id: UUID, name: String, surname: String, fiscalCode: String, address: String) {
    val event = CustomerAcquired(id, name, surname, fiscalCode, address)
    changes.add(event)
  }

  fun changes() = changes.toList()

}

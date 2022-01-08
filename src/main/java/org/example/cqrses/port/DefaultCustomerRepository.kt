package org.example.cqrses.port

import org.example.cqrses.domain.Customer

class DefaultCustomerRepository(
  private val eventStore: EventStore,
  private val eventBus: EventBus
) : CustomerRepository {

  override fun put(customer: Customer) {
    customer.changes().forEach { event ->
      eventStore.append(event)
      eventBus.put(event)
    }
  }

}


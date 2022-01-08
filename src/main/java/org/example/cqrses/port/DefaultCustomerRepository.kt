package org.example.cqrses.port

import com.google.common.eventbus.EventBus
import org.example.cqrses.domain.Customer

class DefaultCustomerRepository(
  private val eventStore: EventStore,
  private val eventBus: EventBus
) : CustomerRepository {

  override fun put(customer: Customer) {
    customer.changes().forEach { event ->
      eventStore.append(event)
        .also {
          eventBus.post(event)
        }
    }
  }

}


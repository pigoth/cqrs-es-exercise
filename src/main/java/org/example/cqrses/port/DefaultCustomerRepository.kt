package org.example.cqrses.port

import com.google.common.eventbus.EventBus
import org.example.cqrses.domain.Customer
import java.util.*

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

  override fun load(customerId: UUID): Customer {
    return Customer().hydrate(eventStore.allFor(customerId))
  }

}


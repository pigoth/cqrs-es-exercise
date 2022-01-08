package org.example.cqrses

import com.google.common.eventbus.EventBus
import org.example.cqrses.domain.AcquireCustomer
import org.example.cqrses.logic.CommandHandler
import org.example.cqrses.logic.DefaultCommandHandler
import org.example.cqrses.logic.DefaultIdGenerator
import org.example.cqrses.logic.IdGenerator
import org.example.cqrses.port.*
import java.util.*

class Bank {
  private val customerViews = InMemoryCustomerViews()
  private val eventBus: EventBus = EventBus().apply {
    this.register(CustomerViewHandler(customerViews))
  }
  private val eventStore: EventStore = InMemoryEventStore()
  private val customerRepository: CustomerRepository = DefaultCustomerRepository(eventStore, eventBus)
  private val idGenerator: IdGenerator = DefaultIdGenerator()
  private val commandHandler: CommandHandler = DefaultCommandHandler(customerRepository)

  fun acquireCustomer(name: String, surname: String, fiscalCode: String, address: String): UUID {
    val id = idGenerator.invoke()
    commandHandler.handle(AcquireCustomer(id, name, surname, fiscalCode, address))
    return id
  }

  fun customers(): List<CustomerView> {
   return customerViews.all()
  }

}

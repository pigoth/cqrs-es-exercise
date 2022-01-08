package org.example.cqrses

import org.example.cqrses.domain.AcquireCustomer
import org.example.cqrses.logic.CommandHandler
import org.example.cqrses.logic.DefaultCommandHandler
import org.example.cqrses.logic.DefaultIdGenerator
import org.example.cqrses.logic.IdGenerator
import org.example.cqrses.port.*
import java.util.*

class Bank(
  private val eventBus: EventBus = FakeEventBus(),
  private val eventStore: EventStore = InMemoryEventStore(),
  private val customerRepository: CustomerRepository = DefaultCustomerRepository(eventStore, eventBus),
  private val commandHandler: CommandHandler = DefaultCommandHandler(customerRepository),
  private val idGenerator: IdGenerator = DefaultIdGenerator()
) {

  fun acquireCustomer(name: String, surname: String, fiscalCode: String, address: String) : UUID {
    val id = idGenerator.invoke()

    commandHandler.handle(AcquireCustomer(id, name, surname, fiscalCode, address))

    return id
  }

  fun  customers(): List<CustomerView> {
    TODO("Not yet implemented")
  }

}

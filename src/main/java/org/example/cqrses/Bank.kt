package org.example.cqrses

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

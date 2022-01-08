package org.example.cqrses

import java.util.*

class Bank(
  private val commandHandler: CommandHandler = DefaultCommandHandler(),
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

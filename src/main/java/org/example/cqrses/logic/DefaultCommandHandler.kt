package org.example.cqrses.logic

import org.example.cqrses.domain.*
import org.example.cqrses.port.CustomerRepository

class DefaultCommandHandler(
  private val customerRepository: CustomerRepository
) : CommandHandler {

  override fun handle(command: Command) {

    val customer = when (command) {
      is AcquireCustomer -> handle(command)
      is ModifyCustomerPersonalData -> handle(command)
      else -> throw Exception("Command not exist")
    }

    customerRepository.put(customer)
  }

  private fun handle(command: AcquireCustomer): Customer {
    val customer = Customer()
    customer.acquire(command.id, command.name, command.surname, command.fiscalCode, command.address)
    return customer
  }

  private fun handle(command: ModifyCustomerPersonalData): Customer {
    val customer = customerRepository.load(command.customerId)
    customer.modifyPersonalData(command.address)
    return customer
  }

}

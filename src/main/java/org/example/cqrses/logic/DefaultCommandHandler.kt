package org.example.cqrses.logic

import org.example.cqrses.domain.AcquireCustomer
import org.example.cqrses.domain.Customer
import org.example.cqrses.port.CustomerRepository

class DefaultCommandHandler(
  private val customerRepository: CustomerRepository
) : CommandHandler {

  override fun handle(command: AcquireCustomer) {
    val customer = Customer()

    customer.acquire(command.id, command.name, command.surname, command.fiscalCode, command.address)

    customerRepository.put(customer)
  }

}

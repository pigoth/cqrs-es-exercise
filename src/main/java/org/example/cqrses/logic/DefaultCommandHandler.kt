package org.example.cqrses.logic

import org.example.cqrses.domain.AcquireCustomer
import org.example.cqrses.domain.Command
import org.example.cqrses.domain.Customer
import org.example.cqrses.domain.ModifyCustomerPersonalData
import org.example.cqrses.port.CustomerRepository

class DefaultCommandHandler(
  private val customerRepository: CustomerRepository
) : CommandHandler {

  override fun handle(command: Command) {

    if (command is AcquireCustomer) {
      val customer = Customer()

      customer.acquire(command.id, command.name, command.surname, command.fiscalCode, command.address)

      customerRepository.put(customer)
    } else if (command is ModifyCustomerPersonalData) {
      //todo
    }
  }

}

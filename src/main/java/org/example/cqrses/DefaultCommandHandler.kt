package org.example.cqrses

class DefaultCommandHandler(
  private val customerRepository: CustomerRepository = DefaultCustomerRepository()
) : CommandHandler {

  override fun handle(command: AcquireCustomer) {
    val customer = Customer()

    customer.acquire(command.id, command.name, command.surname, command.fiscalCode, command.address)

    customerRepository.put(customer)
  }

}

package org.example.cqrses

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.example.cqrses.domain.*
import org.example.cqrses.logic.DefaultCommandHandler
import org.example.cqrses.port.CustomerRepository
import org.junit.jupiter.api.Test
import java.util.UUID.randomUUID

internal class DefaultCommandHandlerTest {

  private val customerRepository: CustomerRepository = mockk()

  @Test
  internal fun should_acquire_a_customer() {
    every { customerRepository.put(any()) } returns Unit

    val commandHandler = DefaultCommandHandler(customerRepository)
    commandHandler.handle(
      AcquireCustomer(
        id = randomUUID(),
        name = "Gino",
        surname = "Paoli",
        fiscalCode = "GinoFiscalCode",
        address = "GinoAddress"
      )
    )

    verify { customerRepository.put(any()) }
  }

  @Test
  internal fun should_modify_customer_personal_data() {
    val customerId = randomUUID()
    every { customerRepository.put(any()) } returns Unit
    every { customerRepository.load(customerId) } returns Customer()
      .hydrate(listOf<Event>(CustomerAcquired(customerId, "Gino", "Paoli", "GinoFiscalCode", "GinoAddress")))

    val commandHandler = DefaultCommandHandler(customerRepository)
    commandHandler.handle(ModifyCustomerPersonalData(customerId, "new address"))

    verify {
      customerRepository.put(match { customer ->
        customer.changes().last() == CustomerPersonalDataModified(customerId, "new address")
      })
    }
  }
}

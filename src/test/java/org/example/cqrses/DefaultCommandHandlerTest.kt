package org.example.cqrses

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.util.*
import java.util.UUID.randomUUID

internal class DefaultCommandHandlerTest {

  private val customerRepository: CustomerRepository = mockk()

  @Test
  internal fun should_acquire_a_customer() {
    every { customerRepository.put(any()) } returns Unit

    val commandHandler = DefaultCommandHandler(customerRepository)
    commandHandler.handle(AcquireCustomer(randomUUID(), "Gino", "Paoli", "GinoFiscalCode", "GinoAddress"))

    verify { customerRepository.put(any()) }
  }

}

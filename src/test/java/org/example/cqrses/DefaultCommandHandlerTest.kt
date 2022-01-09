package org.example.cqrses

import io.mockk.MockKVerificationScope
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.example.cqrses.domain.*
import org.example.cqrses.logic.CommandHandler
import org.example.cqrses.logic.DefaultCommandHandler
import org.example.cqrses.port.CustomerRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.UUID.randomUUID

internal class DefaultCommandHandlerTest {

  private val now: LocalDateTime = LocalDateTime.now()
  private lateinit var commandHandler: CommandHandler
  private val customerRepository: CustomerRepository = mockk()
  private val provideLocalDateTime: ProvideLocalDateTime = mockk()

  @BeforeEach
  internal fun setUp() {
    commandHandler = DefaultCommandHandler(customerRepository, provideLocalDateTime)

    every { customerRepository.put(any()) } returns Unit
    every { provideLocalDateTime.now() } returns now
  }

  @Test
  internal fun should_acquire_a_customer() {
    val id = randomUUID()

    commandHandler.handle(
      AcquireCustomer(
        id = id,
        name = "Gino",
        surname = "Paoli",
        fiscalCode = "GinoFiscalCode",
        address = "GinoAddress"
      )
    )

    verify {
      customerRepository.put(
        lastChangeMatchWith(CustomerAcquired(id, "Gino", "Paoli", "GinoFiscalCode", "GinoAddress", now))
      )
    }
  }

  @Test
  internal fun should_modify_customer_personal_data() {
    val customerId = randomUUID()
    every { customerRepository.load(customerId) } returns Customer()
      .hydrate(
        listOf<Event>(
          CustomerAcquired(customerId, "Gino", "Paoli", "GinoFiscalCode", "GinoAddress", now)
        )
      )

    commandHandler.handle(ModifyCustomerPersonalData(customerId, "new address"))

    verify {
      customerRepository.put(lastChangeMatchWith(CustomerPersonalDataModified(customerId, "new address", now)))
    }
  }

  private fun MockKVerificationScope.lastChangeMatchWith(event: Event): Customer =
    match { customer ->
      customer.changes().last() == event
    }
}

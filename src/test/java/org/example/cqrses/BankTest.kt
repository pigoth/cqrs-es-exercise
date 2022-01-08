package org.example.cqrses

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.example.cqrses.domain.AcquireCustomer
import org.example.cqrses.logic.CommandHandler
import org.example.cqrses.logic.IdGenerator
import org.example.cqrses.port.CustomerRepository
import org.example.cqrses.port.EventBus
import org.example.cqrses.port.EventStore
import org.junit.jupiter.api.Test
import java.util.*

internal class BankTest {

  private val idGenerator = mockk<IdGenerator>()
  private val commandHandler: CommandHandler = mockk()
  private val eventBus: EventBus = mockk()
  private val eventStore: EventStore = mockk()
  private val customerRepository: CustomerRepository = mockk()

  @Test
  internal fun `should acquire a new customer`() {
    val id = UUID.randomUUID()
    every { idGenerator.invoke() } returns id
    every { commandHandler.handle(any()) } returns Unit

    val bank = Bank(eventBus, eventStore, customerRepository, commandHandler, idGenerator)
    bank.acquireCustomer("aName", "aSurname", "fiscalCode", "aFullAddress")

    verify { commandHandler.handle(AcquireCustomer(id, "aName", "aSurname", "fiscalCode", "aFullAddress")) }
  }
}

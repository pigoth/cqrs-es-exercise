package org.example.cqrses

import com.google.common.eventbus.EventBus
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.example.cqrses.domain.CustomerAcquired
import org.example.cqrses.domain.CustomerPersonalDataModified
import org.example.cqrses.domain.Event
import org.example.cqrses.logic.CustomerViewHandler
import org.example.cqrses.port.CustomerView
import org.example.cqrses.port.repository.CustomerViews
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.util.*

internal class CustomerViewHandlerTest {

  private val customerViews: CustomerViews = mockk()
  private lateinit var eventBus: EventBus

  @BeforeEach
  internal fun setUp() {
    eventBus = EventBus()
      .apply { register(CustomerViewHandler(customerViews)) }

    every { customerViews.put(any()) } returns Unit
  }

  @Test
  internal fun should_add_new_costumer_when_acquired() {
    val id = UUID.randomUUID()

    eventBus.post(
      CustomerAcquired(
        id = id,
        name = "Gino",
        surname = "Paoli",
        fiscalCode = "GinoFiscalCode",
        address = "GinoAddress",
        at = now()
      )
    )

    verify { customerViews.put(CustomerView(id, "Gino", "Paoli", "GinoAddress")) }
  }

  @Test
  internal fun should_update_personal_data_when_customer_modified_its() {
    val id = UUID.randomUUID()
    every { customerViews.get(id) } returns CustomerView(id, "Gino", "Paoli", "old address")

    eventBus.post(CustomerPersonalDataModified(id, "new address", now()))

    verify { customerViews.put(CustomerView(id, "Gino", "Paoli", "new address")) }
  }
}

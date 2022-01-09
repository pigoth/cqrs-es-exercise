package org.example.cqrses

import com.google.common.eventbus.EventBus
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.example.cqrses.domain.CustomerAcquired
import org.example.cqrses.logic.CustomerViewHandler
import org.example.cqrses.port.CustomerView
import org.example.cqrses.port.repository.CustomerViews
import org.junit.jupiter.api.Test
import java.util.*

internal class CustomerViewHandlerTest {

  private val customerViews: CustomerViews = mockk()

  @Test
  internal fun should_add_new_costumer_when_acquired() {
    every { customerViews.add(any()) } returns Unit

    val id = UUID.randomUUID()
    EventBus()
      .apply { register(CustomerViewHandler(customerViews)) }
      .apply {
        post(
          CustomerAcquired(
            id = id,
            name = "Gino",
            surname = "Paoli",
            fiscalCode = "GinoFiscalCode",
            address = "GinoAddress"
          )
        )
      }

    verify { customerViews.add(CustomerView(id, "Gino", "Paoli", "GinoAddress")) }
  }

}

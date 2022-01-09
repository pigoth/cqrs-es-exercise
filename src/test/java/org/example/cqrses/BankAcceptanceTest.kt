package org.example.cqrses

import org.assertj.core.api.Assertions.assertThat
import org.example.cqrses.port.CustomerView
import org.junit.jupiter.api.Test

internal class BankAcceptanceTest {

  @Test
  internal fun should_find_acquired_customers() {
    val bank = BankEntryPoint()

    val customerId = bank.acquireCustomer("Gino", "Rossi", "fiscalCode", "full address")

    assertThat(bank.customers()).containsExactly(
      CustomerView(customerId, "Gino", "Rossi", "full address")
    )
  }

  @Test
  internal fun should_update_customer_address() {
    val bank = BankEntryPoint()

    val customerId = bank.acquireCustomer("Gino", "Rossi", "fiscalCode", "old address")
    bank.modifyCustomerPersonalData(customerId, address = "new address")

    assertThat(bank.customers()).containsExactly(
      CustomerView(customerId, "Gino", "Rossi", "new address")
    )
  }
}

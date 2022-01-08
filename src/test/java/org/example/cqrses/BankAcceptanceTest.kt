package org.example.cqrses

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class BankAcceptanceTest {

  @Disabled
  @Test
  internal fun should_find_acquired_customers() {
    val bank = Bank()

    val customerId = bank.acquireCustomer("Gino", "Rossi", "fiscalCode", "full address")

    assertThat(bank.customers()).containsExactly(
      CustomerView(customerId, "Gino", "Rossi", "full address")
    )
  }
}

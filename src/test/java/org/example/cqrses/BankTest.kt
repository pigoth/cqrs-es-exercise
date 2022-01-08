package org.example.cqrses

import org.assertj.core.api.Assertions.assertThat
import org.junit.Ignore
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class BankTest {

  @Disabled
  @Test
  internal fun should_find_acquired_customers() {
    val bank = Bank()

    val customerId = bank.acquireCustomer("Gino", "Rossi")

    assertThat(bank.customers()).containsExactly(
      CustomerView(customerId, "Gino", "Rossi")
    )
  }
}

package org.example.cqrses.port

import org.example.cqrses.domain.Customer
import java.util.*

interface CustomerRepository {
  fun put(customer: Customer)
  fun load(customerId: UUID): Customer
}

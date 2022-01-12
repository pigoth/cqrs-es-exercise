package org.example.cqrses.domain

import java.util.*

interface CustomerRepository {
  fun put(customer: Customer)
  fun load(customerId: UUID): Customer
}

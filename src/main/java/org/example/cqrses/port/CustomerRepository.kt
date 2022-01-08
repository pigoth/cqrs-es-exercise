package org.example.cqrses.port

import org.example.cqrses.domain.Customer

interface CustomerRepository {
  fun put(customer: Customer)
}

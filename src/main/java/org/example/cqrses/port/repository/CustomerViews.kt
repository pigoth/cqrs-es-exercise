package org.example.cqrses.port.repository

import org.example.cqrses.port.CustomerView
import java.util.*

interface CustomerViews {
  fun put(customerView: CustomerView)
  fun all(): List<CustomerView>
  fun get(id: UUID) : CustomerView

}

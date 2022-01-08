package org.example.cqrses

import org.example.cqrses.port.CustomerView

interface CustomerViews {
  fun add(customerView: CustomerView)
  fun all(): List<CustomerView>

}

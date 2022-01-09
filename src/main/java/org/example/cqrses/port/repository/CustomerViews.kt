package org.example.cqrses.port.repository

import org.example.cqrses.port.CustomerView

interface CustomerViews {
  fun add(customerView: CustomerView)
  fun all(): List<CustomerView>

}

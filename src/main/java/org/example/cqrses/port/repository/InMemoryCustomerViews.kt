package org.example.cqrses.port.repository

import org.example.cqrses.port.CustomerView

class InMemoryCustomerViews : CustomerViews {
  private val views = mutableListOf<CustomerView>()

  override fun add(view: CustomerView) {
    views.add(view)
  }

  override fun all(): List<CustomerView> = views.toList()

}

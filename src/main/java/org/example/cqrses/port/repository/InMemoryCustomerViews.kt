package org.example.cqrses.port.repository

import org.example.cqrses.port.CustomerView
import java.util.*

class InMemoryCustomerViews : CustomerViews {
  private val views = mutableMapOf<UUID, CustomerView>()

  override fun put(view: CustomerView) {
    views[view.customerId] = view
  }

  override fun all(): List<CustomerView> = views.values.toList()

  override fun get(id: UUID): CustomerView = views[id]!!

}

package org.example.cqrses.logic

import com.google.common.eventbus.Subscribe
import org.example.cqrses.domain.CustomerAcquired
import org.example.cqrses.port.CustomerView
import org.example.cqrses.port.repository.CustomerViews

class CustomerViewHandler(private val customerViews: CustomerViews) {

  @Subscribe
  fun consume(event: CustomerAcquired) {
    customerViews.add(CustomerView(event.id, event.name, event.surname, event.address))
  }

}

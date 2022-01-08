package org.example.cqrses.port

import org.example.cqrses.domain.Event

class InMemoryEventStore : EventStore {

  var events = mutableListOf<Event>()

  override fun append(event: Event) {
    events.add(event)
  }

}

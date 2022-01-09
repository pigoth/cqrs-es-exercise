package org.example.cqrses.port

import org.example.cqrses.domain.Event
import java.util.*

class InMemoryEventStore : EventStore {

  var events = mutableListOf<Event>()

  override fun append(event: Event) {
    events.add(event)
  }

  override fun allFor(id: UUID): List<Event> {
    return events.filter { it.id == id }
  }

}

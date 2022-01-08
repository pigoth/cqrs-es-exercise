package org.example.cqrses

class InMemoryEventStore : EventStore {

  var events = mutableListOf<Event>()

  override fun append(event: Event) {
    events.add(event)
  }

}

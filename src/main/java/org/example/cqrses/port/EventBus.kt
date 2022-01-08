package org.example.cqrses.port

import org.example.cqrses.domain.Event

interface EventBus {
  fun put(event: Event)

}

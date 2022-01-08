package org.example.cqrses

interface EventBus {
  fun put(event: Event)

}

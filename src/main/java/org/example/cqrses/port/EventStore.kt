package org.example.cqrses.port

import org.example.cqrses.domain.Event

interface EventStore {
   fun append(event: Event)
}

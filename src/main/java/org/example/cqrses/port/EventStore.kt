package org.example.cqrses.port

import org.example.cqrses.domain.Event
import java.util.*

interface EventStore {
   fun append(event: Event)
   fun allFor(id: UUID) : List<Event>
}

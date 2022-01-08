package org.example.cqrses

interface EventStore {
   fun append(event: Event)
}

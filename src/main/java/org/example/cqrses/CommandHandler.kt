package org.example.cqrses

interface CommandHandler {
  fun handle(acquireCustomer: AcquireCustomer)
}

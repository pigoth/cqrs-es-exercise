package org.example.cqrses.logic

import org.example.cqrses.domain.AcquireCustomer

interface CommandHandler {
  fun handle(acquireCustomer: AcquireCustomer)
}

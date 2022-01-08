package org.example.cqrses.logic

import java.util.*

class DefaultIdGenerator : IdGenerator {
  override fun invoke(): UUID = UUID.randomUUID()
}

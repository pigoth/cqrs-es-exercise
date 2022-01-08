package org.example.cqrses

import java.util.*

class DefaultIdGenerator : IdGenerator {
  override fun invoke(): UUID = UUID.randomUUID()
}

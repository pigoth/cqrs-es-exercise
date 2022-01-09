package org.example.cqrses

import java.time.LocalDateTime
import java.time.ZoneOffset.UTC

interface ProvideLocalDateTime {
  fun now() :LocalDateTime
}

class UTCLocalDateTime : ProvideLocalDateTime {
  override fun now(): LocalDateTime = LocalDateTime.now(UTC)
}

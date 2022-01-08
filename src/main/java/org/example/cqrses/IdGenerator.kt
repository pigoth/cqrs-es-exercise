package org.example.cqrses

import java.util.*


interface IdGenerator {
  fun invoke () : UUID
}

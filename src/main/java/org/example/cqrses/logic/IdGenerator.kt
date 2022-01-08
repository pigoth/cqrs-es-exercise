package org.example.cqrses.logic

import java.util.*


interface IdGenerator {
  fun invoke () : UUID
}

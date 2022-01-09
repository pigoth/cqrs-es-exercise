package org.example.cqrses.logic

import org.example.cqrses.domain.Command

interface CommandHandler {
  fun handle(command: Command)
}

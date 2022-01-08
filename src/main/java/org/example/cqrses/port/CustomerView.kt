package org.example.cqrses.port

import java.util.*

data class CustomerView(val customerId: UUID, val name: String, val surname: String, val fullAddress: String)

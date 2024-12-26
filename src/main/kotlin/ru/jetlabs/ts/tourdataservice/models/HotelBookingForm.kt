package ru.jetlabs.ts.tourdataservice.models

import java.time.LocalDateTime

data class HotelBookingForm(
    val tourId: Long,
    val hotelId: Long,
    val roomId: Long,
    val nutritionId: Long,
    val startBookingDate: LocalDateTime,
    val endBookingDate: LocalDateTime,
)
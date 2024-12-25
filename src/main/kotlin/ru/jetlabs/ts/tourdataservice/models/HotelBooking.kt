package ru.jetlabs.ts.tourdataservice.models

import ru.jetlabs.ts.tourdataservice.daos.HotelBookingDao
import java.time.LocalDateTime

data class HotelBooking(
    val id: Long,
    val hotel: Hotel,
    val room: HotelRoom,
    val nutrition: HotelNutrition,
    val startBookingDate: LocalDateTime,
    val endBookingDate: LocalDateTime,
    val costPerDay: Double
)

fun HotelBookingDao.mapToHotelBooking(): HotelBooking = HotelBooking(
    id = id.value,
    hotel = hotel.mapToHotel(),
    room = room.mapToHotelRoom(),
    nutrition = nutrition.mapToHotelNutrition(),
    startBookingDate = startBookingDate,
    endBookingDate = endBookingDate,
    costPerDay = 121.2
)
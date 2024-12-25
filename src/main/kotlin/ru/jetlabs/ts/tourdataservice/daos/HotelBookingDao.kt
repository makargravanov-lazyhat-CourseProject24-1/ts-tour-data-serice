package ru.jetlabs.ts.tourdataservice.daos

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.jetlabs.ts.tourdataservice.tables.HotelBookings

class HotelBookingDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<HotelBookingDao>(HotelBookings)

    var tourId by HotelBookings.tourId
    var hotel by HotelDao referencedOn HotelBookings.hotel
    var room by HotelRoomDao referencedOn HotelBookings.room
    var nutrition by HotelNutritionDao referencedOn HotelBookings.nutrition
    var startBookingDate by HotelBookings.startBookingDate
    var endBookingDate by HotelBookings.endBookingDate
}
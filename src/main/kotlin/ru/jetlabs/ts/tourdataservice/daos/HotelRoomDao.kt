package ru.jetlabs.ts.tourdataservice.daos

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.jetlabs.ts.tourdataservice.tables.HotelRooms

class HotelRoomDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<HotelRoomDao>(HotelRooms)

    var hotel by HotelDao referencedOn HotelRooms.hotel
    var capacity by HotelRooms.capacity
    var type by HotelRooms.type
    var wifi by HotelRooms.wifi
    var costPerDay by HotelRooms.costPerDay
}
package ru.jetlabs.ts.tourdataservice.models

import ru.jetlabs.ts.tourdataservice.daos.HotelRoomDao
import ru.jetlabs.ts.tourdataservice.models.enums.RoomCapacity
import ru.jetlabs.ts.tourdataservice.models.enums.RoomType

data class HotelRoom(
    val id: Long,
    val hotel: Hotel,
    val capacity: RoomCapacity,
    val type: RoomType,
    val wifi: Boolean,
    val costPerDay: Double
)

fun HotelRoomDao.mapToHotelRoom(): HotelRoom = HotelRoom(
    id = id.value,
    hotel = hotel.mapToHotel(),
    capacity = capacity,
    type = type,
    wifi = wifi,
    costPerDay = costPerDay
)
package ru.jetlabs.ts.tourdataservice.models

import ru.jetlabs.ts.tourdataservice.daos.HotelDao
import ru.jetlabs.ts.tourdataservice.models.enums.HotelLevel

data class Hotel(
    val id: Long,
    val name: String,
    val level: HotelLevel,
    val place: Place
)

fun HotelDao.mapToHotel(): Hotel = Hotel(
    id = id.value,
    name = name,
    level = level,
    place = place.mapToPlace()
)
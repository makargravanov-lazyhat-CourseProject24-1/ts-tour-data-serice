package ru.jetlabs.ts.tourdataservice.service

import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.jetlabs.ts.tourdataservice.models.Hotel
import ru.jetlabs.ts.tourdataservice.models.enums.HotelLevel
import ru.jetlabs.ts.tourdataservice.models.mapToHotel
import ru.jetlabs.ts.tourdataservice.tables.Hotels

@Component
@Transactional
class HotelsService {
    fun getHotels(
        level: HotelLevel? = null, placeId: Long? = null, name: String? = null
    ): List<Hotel> = Hotels.selectAll().where {
        (if (level != null) Hotels.level eq level else Op.TRUE) and (if (placeId != null) Hotels.place eq placeId else Op.TRUE) and (if (name != null) Hotels.name like name else Op.TRUE)
    }.map { it.mapToHotel() }
}
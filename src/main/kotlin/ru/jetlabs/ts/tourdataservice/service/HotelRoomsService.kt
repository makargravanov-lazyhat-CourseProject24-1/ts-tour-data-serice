package ru.jetlabs.ts.tourdataservice.service

import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.and
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.jetlabs.ts.tourdataservice.daos.HotelRoomDao
import ru.jetlabs.ts.tourdataservice.models.HotelRoom
import ru.jetlabs.ts.tourdataservice.models.enums.RoomCapacity
import ru.jetlabs.ts.tourdataservice.models.enums.RoomType
import ru.jetlabs.ts.tourdataservice.models.mapToHotelRoom
import ru.jetlabs.ts.tourdataservice.tables.HotelRooms

@Component
@Transactional
class HotelRoomsService {
    fun getRooms(hotelId: Long?, capacity: RoomCapacity?, type: RoomType?, wifi: Boolean?): List<HotelRoom> =
        HotelRoomDao.find {
            (if (capacity != null) HotelRooms.capacity eq capacity else Op.TRUE) and
                    (if (type != null) HotelRooms.type eq type else Op.TRUE) and
                    (if (wifi != null) HotelRooms.wifi eq wifi else Op.TRUE) and
                    (if (hotelId != null) HotelRooms.hotel eq hotelId else Op.TRUE)
        }.map { it.mapToHotelRoom() }
}


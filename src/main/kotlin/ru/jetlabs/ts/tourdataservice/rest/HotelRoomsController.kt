package ru.jetlabs.ts.tourdataservice.rest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.jetlabs.ts.tourdataservice.models.GetRoomByIdResult
import ru.jetlabs.ts.tourdataservice.models.HotelRoom
import ru.jetlabs.ts.tourdataservice.models.enums.RoomCapacity
import ru.jetlabs.ts.tourdataservice.models.enums.RoomType
import ru.jetlabs.ts.tourdataservice.service.HotelRoomsService

@RestController
@RequestMapping("/ts-tour-data-service/api/v1/rooms")
class HotelRoomsController(
    val roomsService: HotelRoomsService
) {
    @GetMapping
    fun getRooms(
        @RequestParam hotelId: Long?,
        @RequestParam capacity: RoomCapacity?,
        @RequestParam type: RoomType?,
        @RequestParam wifi: Boolean?
    ): ResponseEntity<List<HotelRoom>> =
        roomsService.getRooms(hotelId = hotelId, capacity = capacity, type = type, wifi = wifi).let {
            ResponseEntity.status(HttpStatus.OK).body(it)
        }

    @GetMapping("/{id}")
    fun getRoomById(@PathVariable id: Long): ResponseEntity<HotelRoom> = roomsService.getRoomById(id).let {
        when (it) {
            GetRoomByIdResult.NotFound -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
            is GetRoomByIdResult.Success -> ResponseEntity.status(HttpStatus.OK).body(it.data)
        }
    }
}


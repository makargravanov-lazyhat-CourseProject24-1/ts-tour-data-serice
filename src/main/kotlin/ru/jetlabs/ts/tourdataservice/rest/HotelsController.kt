package ru.jetlabs.ts.tourdataservice.rest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.jetlabs.ts.tourdataservice.models.GetHotelByIdResult
import ru.jetlabs.ts.tourdataservice.models.Hotel
import ru.jetlabs.ts.tourdataservice.models.enums.HotelLevel
import ru.jetlabs.ts.tourdataservice.service.HotelsService

@RestController
@RequestMapping("/ts-tour-data-service/api/v1/hotels")
class HotelsController(
    private val hotelsService: HotelsService
) {
    @GetMapping
    fun getHotels(
        @RequestParam level: HotelLevel?, @RequestParam placeId: Long?, @RequestParam name: String?
    ): ResponseEntity<List<Hotel>> =
        hotelsService.getHotels(level = level, placeId = placeId, name = name).let { ResponseEntity.status(HttpStatus.OK).body(it) }

    @GetMapping("/{id}")
    fun getHotelById( @PathVariable id: Long): ResponseEntity<Hotel> = hotelsService.getHotelById(id).let {
        when (it) {
            GetHotelByIdResult.NotFound -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
            is GetHotelByIdResult.Success -> ResponseEntity.status(HttpStatus.OK).body(it.data)
        }
    }
}
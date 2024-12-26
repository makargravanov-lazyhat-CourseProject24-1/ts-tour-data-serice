package ru.jetlabs.ts.tourdataservice.rest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
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
}
package ru.jetlabs.ts.tourdataservice.rest

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.jetlabs.ts.tourdataservice.service.PlacesService

@RestController
@RequestMapping("/ts-tour-data-service/api/v1/places")
class PlacesController(
    private val placesService: PlacesService,
) {
    @GetMapping
    fun getPlaces(@RequestParam name: String?, @RequestParam address: String?): ResponseEntity<*> =
        placesService.getPlaces(
            name = name,
            address = address
        ).let { ResponseEntity.ok(it) }
}
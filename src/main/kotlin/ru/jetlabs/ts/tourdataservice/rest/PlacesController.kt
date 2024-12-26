package ru.jetlabs.ts.tourdataservice.rest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.jetlabs.ts.tourdataservice.models.GetPlaceByIdResult
import ru.jetlabs.ts.tourdataservice.models.Place
import ru.jetlabs.ts.tourdataservice.service.PlacesService

@RestController
@RequestMapping("/ts-tour-data-service/api/v1/places")
class PlacesController(
    private val placesService: PlacesService,
) {
    @GetMapping
    fun getPlaces(@RequestParam name: String?, @RequestParam address: String?): ResponseEntity<List<Place>> =
        placesService.getPlaces(
            name = name,
            address = address
        ).let { ResponseEntity.ok(it) }

    @GetMapping("/{id}")
    fun getPlaceById(@PathVariable id: Long): ResponseEntity<Place> = placesService.getPlaceById(id).let {
        when (it) {
            GetPlaceByIdResult.NotFound -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
            is GetPlaceByIdResult.Success -> ResponseEntity.status(HttpStatus.OK).body(it.data)
        }
    }
}
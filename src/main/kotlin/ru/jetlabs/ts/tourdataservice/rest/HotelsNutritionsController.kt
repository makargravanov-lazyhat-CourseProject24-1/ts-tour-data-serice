package ru.jetlabs.ts.tourdataservice.rest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.jetlabs.ts.tourdataservice.models.GetNutritionByIdResult
import ru.jetlabs.ts.tourdataservice.models.HotelNutrition
import ru.jetlabs.ts.tourdataservice.models.enums.NutritionType
import ru.jetlabs.ts.tourdataservice.service.HotelNutritionsService

@RestController
@RequestMapping("/ts-tour-data-service/api/v1/nutritions")
class HotelsNutritionsController(
    private val hotelNutritionsService: HotelNutritionsService
) {
    @GetMapping
    fun getNutritions(
        @RequestParam type: NutritionType?, @RequestParam costPerDay: Double?
    ): ResponseEntity<List<HotelNutrition>> =
        hotelNutritionsService.getHotelNutritions(type = type, costPerDay = costPerDay).let {
            ResponseEntity.status(HttpStatus.OK)
                .body(it)
        }

    @GetMapping("/{id}")
    fun getNutritionById(@PathVariable id: Long): ResponseEntity<HotelNutrition> =
        hotelNutritionsService.getHotelNutritionById(id).let {
            when (it) {
                GetNutritionByIdResult.NotFound -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
                is GetNutritionByIdResult.Success -> ResponseEntity.status(HttpStatus.OK).body(it.data)
            }
        }
}
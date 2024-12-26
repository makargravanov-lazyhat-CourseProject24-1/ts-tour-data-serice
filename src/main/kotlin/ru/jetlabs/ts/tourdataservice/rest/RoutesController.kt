package ru.jetlabs.ts.tourdataservice.rest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.jetlabs.ts.tourdataservice.models.TransportRoute
import ru.jetlabs.ts.tourdataservice.models.enums.TransportType
import ru.jetlabs.ts.tourdataservice.service.RoutesService

@RestController
@RequestMapping("/ts-tour-data-service/api/v1/routes")
class RoutesController(
    private val routesService: RoutesService
) {
    @GetMapping
    fun getRoutes(
        @RequestParam name: String?,
        @RequestParam departurePlaceId: Long?,
        @RequestParam arrivePlaceId: Long?,
        @RequestParam transportType: TransportType?,
    ): ResponseEntity<List<TransportRoute>> = routesService.getRoutes(
        name = name,
        departurePlaceId = departurePlaceId,
        arrivePlaceId = arrivePlaceId,
        transportType = transportType
    ).let { ResponseEntity.status(HttpStatus.OK).body(it) }
}
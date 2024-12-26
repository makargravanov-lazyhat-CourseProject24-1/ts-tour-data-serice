package ru.jetlabs.ts.tourdataservice.rest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.jetlabs.ts.tourdataservice.models.GetRouteByIdResult
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

    @GetMapping("/{id}")
    fun getRouteById(@PathVariable id: Long): ResponseEntity<TransportRoute> = routesService.getRouteById(id).let {
        when (it) {
            GetRouteByIdResult.NotFound -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
            is GetRouteByIdResult.Success -> ResponseEntity.status(HttpStatus.OK).body(it.data)
        }
    }
}
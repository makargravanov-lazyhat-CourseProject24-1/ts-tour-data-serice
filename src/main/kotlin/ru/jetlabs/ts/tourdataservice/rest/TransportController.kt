package ru.jetlabs.ts.tourdataservice.rest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.jetlabs.ts.tourdataservice.models.GetTransportByIdResult
import ru.jetlabs.ts.tourdataservice.models.Transport
import ru.jetlabs.ts.tourdataservice.models.enums.TransportType
import ru.jetlabs.ts.tourdataservice.service.TransportService

@RestController
@RequestMapping("/ts-tour-data-service/api/v1/transport")
class TransportController(
    val transportService: TransportService
) {
    @GetMapping
    fun getTransports(
        @RequestParam name: String?,
        @RequestParam type: TransportType?,
        @RequestParam contractorId: Long?
    ): ResponseEntity<List<Transport>> =
        transportService.getTransports(type = type, name = name, contractorId = contractorId).let {
            ResponseEntity.status(HttpStatus.OK).body(it)
        }

    @GetMapping("/{id}")
    fun getHotelById(@PathVariable id: Long): ResponseEntity<Transport> = transportService.getTransportById(id).let {
        when (it) {
            GetTransportByIdResult.NotFound -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
            is GetTransportByIdResult.Success -> ResponseEntity.status(HttpStatus.OK).body(it.data)
        }
    }
}
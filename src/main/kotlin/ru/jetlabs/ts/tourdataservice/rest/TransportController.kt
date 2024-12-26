package ru.jetlabs.ts.tourdataservice.rest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
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
    ): ResponseEntity<List<Transport>> = transportService.getTransports(type = type, name = name, contractorId = contractorId).let {
        ResponseEntity.status(HttpStatus.OK).body(it)
    }
}
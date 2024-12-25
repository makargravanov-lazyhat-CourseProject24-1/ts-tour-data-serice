package ru.jetlabs.ts.tourdataservice.rest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.jetlabs.ts.tourdataservice.models.results.GetBookingDataByTicketId
import ru.jetlabs.ts.tourdataservice.models.results.GetBookingDataByTourIdResult
import ru.jetlabs.ts.tourdataservice.service.BookingService

@RestController
@RequestMapping("/ts-tour-data-service/api/v1/bookings")
class BookingsController(
    private val bookingService: BookingService
) {
    @GetMapping("/bytour/{id}")
    fun getBookingDataByTourId(@PathVariable("id") id: Long): ResponseEntity<*> =
        bookingService.getBookingDataByTourId(tourId = id).let {
            when (it) {
                is GetBookingDataByTourIdResult.Success -> ResponseEntity.status(HttpStatus.OK).body(it.data)
                is GetBookingDataByTourIdResult.NotFound -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(it)
            }
        }

    @GetMapping("/byticket/{id}")
    fun getBookingDataByTicketId(@PathVariable("id") id: Long): ResponseEntity<*> =
        bookingService.getBookingDataByTicketId(ticket = id).let {
            when (it) {
                is GetBookingDataByTicketId.Success -> ResponseEntity.status(HttpStatus.OK).body(it.data)
            }
        }
}
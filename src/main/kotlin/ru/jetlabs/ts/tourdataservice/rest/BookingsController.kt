package ru.jetlabs.ts.tourdataservice.rest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.jetlabs.ts.tourdataservice.models.results.GetBookingDataByTicketId
import ru.jetlabs.ts.tourdataservice.models.results.GetBookingDataByTourIdResult
import ru.jetlabs.ts.tourdataservice.service.BookHotelResult
import ru.jetlabs.ts.tourdataservice.service.BookingService
import ru.jetlabs.ts.tourdataservice.service.HotelBookingForm

@RestController
@RequestMapping("/ts-tour-data-service/api/v1/bookings")
class BookingsController(
    private val bookingService: BookingService
) {
    @GetMapping("/hotel/{id}")
    fun getBookingDataByTourId(@PathVariable("id") id: Long): ResponseEntity<*> =
        bookingService.getBookingDataByTourId(tourId = id).let {
            when (it) {
                is GetBookingDataByTourIdResult.Success -> ResponseEntity.status(HttpStatus.OK).body(it.data)
                is GetBookingDataByTourIdResult.NotFound -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(it)
            }
        }

    @GetMapping("/route/{id}")
    fun getBookingDataByTicketId(@PathVariable("id") id: Long): ResponseEntity<*> =
        bookingService.getBookingDataByTicketId(ticket = id).let {
            when (it) {
                is GetBookingDataByTicketId.Success -> ResponseEntity.status(HttpStatus.OK).body(it.data)
            }
        }

    @PostMapping("/hotel")
    fun bookHotel(@RequestBody body: HotelBookingForm): ResponseEntity<*> = bookingService.bookHotel(form = body).let {
        when (it) {
            is BookHotelResult.Success -> ResponseEntity.status(HttpStatus.OK).body(it.hotelBooking)
            is BookHotelResult.Error -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(it)
        }
    }
}
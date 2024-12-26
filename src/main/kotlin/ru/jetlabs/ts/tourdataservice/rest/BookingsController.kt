package ru.jetlabs.ts.tourdataservice.rest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.jetlabs.ts.tourdataservice.models.HotelBookingForm
import ru.jetlabs.ts.tourdataservice.models.RouteBookingForm
import ru.jetlabs.ts.tourdataservice.models.results.GetBookingDataByTicketId
import ru.jetlabs.ts.tourdataservice.models.results.GetBookingDataByTourIdResult
import ru.jetlabs.ts.tourdataservice.models.results.HotelBookingResult
import ru.jetlabs.ts.tourdataservice.models.results.RouteBookingResult
import ru.jetlabs.ts.tourdataservice.service.BookingService

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
            is HotelBookingResult.Success -> ResponseEntity.status(HttpStatus.OK).body(it.hotelBooking)
            is HotelBookingResult.Error -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(it)
        }
    }

    @PostMapping("/route")
    fun bookRoute(@RequestBody body: RouteBookingForm): ResponseEntity<*> = bookingService.bookRoute(form = body).let {
        when(it){
            is RouteBookingResult.Success -> ResponseEntity.status(HttpStatus.OK).body(it.routeBooking)
            is RouteBookingResult.Error -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(it)
        }
    }
}
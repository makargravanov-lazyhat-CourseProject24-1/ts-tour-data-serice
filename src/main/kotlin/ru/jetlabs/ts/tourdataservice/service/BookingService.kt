package ru.jetlabs.ts.tourdataservice.service

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.jetlabs.ts.tourdataservice.daos.HotelBookingDao
import ru.jetlabs.ts.tourdataservice.daos.RouteBookingDao
import ru.jetlabs.ts.tourdataservice.models.mapToHotelBooking
import ru.jetlabs.ts.tourdataservice.models.mapToRouteBooking
import ru.jetlabs.ts.tourdataservice.models.results.GetBookingDataByTicketId
import ru.jetlabs.ts.tourdataservice.models.results.GetBookingDataByTourIdResult
import ru.jetlabs.ts.tourdataservice.tables.HotelBookings
import ru.jetlabs.ts.tourdataservice.tables.RouteBookings

@Component
@Transactional
class BookingService {
    fun getBookingDataByTourId(tourId: Long): GetBookingDataByTourIdResult =
        HotelBookingDao.find { HotelBookings.tourId eq tourId }.singleOrNull()?.mapToHotelBooking()?.let {
            GetBookingDataByTourIdResult.Success(it)
        } ?: GetBookingDataByTourIdResult.NotFound

    fun getBookingDataByTicketId(ticket: Long): GetBookingDataByTicketId =
        RouteBookingDao.find { RouteBookings.ticketId eq ticket }.map { it.mapToRouteBooking() }
            .let { GetBookingDataByTicketId.Success(it) }
}
package ru.jetlabs.ts.tourdataservice.service

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.jetlabs.ts.tourdataservice.daos.*
import ru.jetlabs.ts.tourdataservice.models.HotelBookingForm
import ru.jetlabs.ts.tourdataservice.models.RouteBookingForm
import ru.jetlabs.ts.tourdataservice.models.mapToHotelBooking
import ru.jetlabs.ts.tourdataservice.models.mapToRouteBooking
import ru.jetlabs.ts.tourdataservice.models.results.GetBookingDataByTicketId
import ru.jetlabs.ts.tourdataservice.models.results.GetBookingDataByTourIdResult
import ru.jetlabs.ts.tourdataservice.models.results.HotelBookingResult
import ru.jetlabs.ts.tourdataservice.models.results.RouteBookingResult
import ru.jetlabs.ts.tourdataservice.tables.HotelBookings
import ru.jetlabs.ts.tourdataservice.tables.RouteBookings
import java.sql.SQLException

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

    fun bookHotel(form: HotelBookingForm): HotelBookingResult {
        try {
            val hotelDao =
                HotelDao.findById(form.hotelId) ?: return HotelBookingResult.Error.HotelNotFound(form.hotelId)
            val roomDao =
                HotelRoomDao.findById(form.roomId) ?: return HotelBookingResult.Error.HotelRoomNotFound(form.roomId)
            val nutritionDao =
                HotelNutritionDao.findById(form.nutritionId)
                    ?: return HotelBookingResult.Error.HotelNutritionNotFound(form.nutritionId)
            return HotelBookingDao.new {
                tourId = form.tourId
                hotel = hotelDao
                room = roomDao
                nutrition = nutritionDao
                startBookingDate = form.startBookingDate
                endBookingDate = form.endBookingDate
            }.mapToHotelBooking().let { HotelBookingResult.Success(it) }
        } catch (e: SQLException) {
            return HotelBookingResult.Error.UnknownError(e.stackTraceToString())
        }
    }

    fun bookRoute(form: RouteBookingForm): RouteBookingResult {
        try {
            val routeDao =
                TransportRouteDao.findById(form.routeId) ?: return RouteBookingResult.Error.RouteNotFound(form.routeId)
            return RouteBookingDao.new {
                ticketId = form.ticketId
                route = routeDao
                personCount = form.personCount
            }.mapToRouteBooking().let {
                RouteBookingResult.Success(it)
            }
        } catch (e: SQLException) {
            return RouteBookingResult.Error.UnknownError(e.stackTraceToString())
        }
    }
}


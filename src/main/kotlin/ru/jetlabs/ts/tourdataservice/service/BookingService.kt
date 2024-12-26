package ru.jetlabs.ts.tourdataservice.service

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.jetlabs.ts.tourdataservice.daos.*
import ru.jetlabs.ts.tourdataservice.models.HotelBooking
import ru.jetlabs.ts.tourdataservice.models.mapToHotelBooking
import ru.jetlabs.ts.tourdataservice.models.mapToRouteBooking
import ru.jetlabs.ts.tourdataservice.models.results.GetBookingDataByTicketId
import ru.jetlabs.ts.tourdataservice.models.results.GetBookingDataByTourIdResult
import ru.jetlabs.ts.tourdataservice.tables.HotelBookings
import ru.jetlabs.ts.tourdataservice.tables.RouteBookings
import java.sql.SQLException
import java.time.LocalDateTime

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

    fun bookHotel(form: HotelBookingForm): BookHotelResult {
        try {
            val hotelDao = HotelDao.findById(form.hotelId) ?: return BookHotelResult.Error.HotelNotFound(form.hotelId)
            val roomDao =
                HotelRoomDao.findById(form.roomId) ?: return BookHotelResult.Error.HotelRoomNotFound(form.roomId)
            val nutritionDao =
                HotelNutritionDao.findById(form.nutritionId)
                    ?: return BookHotelResult.Error.HotelNutritionNotFound(form.nutritionId)
            return HotelBookingDao.new {
                tourId = form.tourId
                hotel = hotelDao
                room = roomDao
                nutrition = nutritionDao
                startBookingDate = form.startBookingDate
                endBookingDate = form.endBookingDate
            }.mapToHotelBooking().let { BookHotelResult.Success(it) }
        } catch (e: SQLException) {
            return BookHotelResult.Error.UnknownError(e.stackTraceToString())
        }
    }
}

sealed interface BookHotelResult {
    data class Success(val hotelBooking: HotelBooking) : BookHotelResult
    sealed interface Error : BookHotelResult {
        val message: String

        data class HotelNotFound(val id: Long) : Error {
            override val message: String = "Hotel with id = $id not found"
        }

        data class HotelRoomNotFound(val id: Long) : Error {
            override val message: String = "Hotel room with id = $id not found"
        }

        data class HotelNutritionNotFound(val id: Long) : Error {
            override val message: String = "Nutrition with id = $id not found"
        }

        data class UnknownError(override val message: String) : Error
    }
}

data class HotelBookingForm(
    val tourId: Long,
    val hotelId: Long,
    val roomId: Long,
    val nutritionId: Long,
    val startBookingDate: LocalDateTime,
    val endBookingDate: LocalDateTime,
)
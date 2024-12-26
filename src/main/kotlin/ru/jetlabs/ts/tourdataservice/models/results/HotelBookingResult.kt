package ru.jetlabs.ts.tourdataservice.models.results

import ru.jetlabs.ts.tourdataservice.models.HotelBooking

sealed interface HotelBookingResult {
    data class Success(val hotelBooking: HotelBooking) : HotelBookingResult
    sealed interface Error : HotelBookingResult {
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
package ru.jetlabs.ts.tourdataservice.models.results

import ru.jetlabs.ts.tourdataservice.models.HotelBooking

sealed interface GetBookingDataByTourIdResult {
    data class Success(val data: HotelBooking) : GetBookingDataByTourIdResult
    data object NotFound : GetBookingDataByTourIdResult
}
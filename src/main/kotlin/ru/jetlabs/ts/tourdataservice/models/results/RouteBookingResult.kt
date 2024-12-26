package ru.jetlabs.ts.tourdataservice.models.results

import ru.jetlabs.ts.tourdataservice.models.RouteBooking

sealed interface RouteBookingResult {
    data class Success(val routeBooking: RouteBooking) : RouteBookingResult
    sealed interface Error : RouteBookingResult {
        val message: String

        data class RouteNotFound(val id: Long) : Error {
            override val message: String = "Route with id = $id not found"
        }

        data class UnknownError(override val message: String) : Error
    }
}
package ru.jetlabs.ts.tourdataservice.models.results

import ru.jetlabs.ts.tourdataservice.models.RouteBooking

sealed interface GetBookingDataByTicketId {
    data class Success(val data: List<RouteBooking>) : GetBookingDataByTicketId
}
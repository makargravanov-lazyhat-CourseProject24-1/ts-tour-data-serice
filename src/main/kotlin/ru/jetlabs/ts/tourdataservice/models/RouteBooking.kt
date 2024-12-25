package ru.jetlabs.ts.tourdataservice.models

import ru.jetlabs.ts.tourdataservice.daos.RouteBookingDao

data class RouteBooking(
    val id: Long,
    val ticketId: Long,
    val route: TransportRoute,
    val personCount: Int
)

fun RouteBookingDao.mapToRouteBooking() : RouteBooking = RouteBooking(
    id = id.value,
    ticketId = ticketId,
    route = route.mapToTransportRoute(),
    personCount = personCount
)
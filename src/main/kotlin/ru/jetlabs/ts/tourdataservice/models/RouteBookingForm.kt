package ru.jetlabs.ts.tourdataservice.models

data class RouteBookingForm(
    val ticketId: Long,
    val routeId: Long,
    val personCount: Int
)
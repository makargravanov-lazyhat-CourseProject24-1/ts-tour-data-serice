package ru.jetlabs.ts.tourdataservice.models

import ru.jetlabs.ts.tourdataservice.daos.TransportRouteDao
import java.time.LocalDateTime

data class TransportRoute(
    val id: Long,
    val name: String,
    val transport: Transport,
    val departurePlace: Place,
    val arrivePlace: Place,
    val departureTime: LocalDateTime,
    val arrivalTime: LocalDateTime,
    val cost: Double,
    val capacity: Int
)

fun TransportRouteDao.mapToTransportRoute(): TransportRoute = TransportRoute(
    id = id.value,
    name = name,
    transport = transport.mapToTransport(),
    departurePlace = departurePlace.mapToPlace(),
    arrivePlace = arrivePlace.mapToPlace(),
    departureTime = departureTime,
    arrivalTime = arriveTime,
    cost = cost,
    capacity = capacity
)
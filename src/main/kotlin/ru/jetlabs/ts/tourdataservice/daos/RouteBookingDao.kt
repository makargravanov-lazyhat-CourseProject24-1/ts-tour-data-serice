package ru.jetlabs.ts.tourdataservice.daos

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.jetlabs.ts.tourdataservice.tables.RouteBookings

class RouteBookingDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<RouteBookingDao>(RouteBookings)

    var ticketId by RouteBookings.ticketId
    var route by TransportRouteDao referencedOn RouteBookings.route
    var personCount by RouteBookings.personCount
}
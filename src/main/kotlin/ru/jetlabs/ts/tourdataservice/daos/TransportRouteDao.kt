package ru.jetlabs.ts.tourdataservice.daos

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.jetlabs.ts.tourdataservice.tables.TransportRoutes

class TransportRouteDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<TransportRouteDao>(TransportRoutes)

    var name by TransportRoutes.name
    var transport by TransportDao referencedOn TransportRoutes.transport
    var departurePlace by PlaceDao referencedOn TransportRoutes.departurePlace
    var arrivePlace by PlaceDao referencedOn TransportRoutes.arrivePlace
    var arriveTime by TransportRoutes.arriveTime
    var departureTime by TransportRoutes.departureTime
    var cost by TransportRoutes.cost
    var capacity by TransportRoutes.capacity
}
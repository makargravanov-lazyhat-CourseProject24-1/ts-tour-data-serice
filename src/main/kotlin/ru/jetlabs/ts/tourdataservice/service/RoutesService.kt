package ru.jetlabs.ts.tourdataservice.service

import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.and
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.jetlabs.ts.tourdataservice.daos.TransportRouteDao
import ru.jetlabs.ts.tourdataservice.models.TransportRoute
import ru.jetlabs.ts.tourdataservice.models.enums.TransportType
import ru.jetlabs.ts.tourdataservice.models.mapToTransportRoute
import ru.jetlabs.ts.tourdataservice.tables.TransportRoutes

@Component
@Transactional
class RoutesService {
    fun getRoutes(
        name: String?,
        departurePlaceId: Long?,
        arrivePlaceId: Long?,
        transportType: TransportType?,
    ): List<TransportRoute> = TransportRouteDao.find {
        (if (name != null) TransportRoutes.name like name else Op.TRUE) and (if (departurePlaceId != null) TransportRoutes.departurePlace eq departurePlaceId else Op.TRUE) and
                (if (arrivePlaceId != null) TransportRoutes.arrivePlace eq arrivePlaceId else Op.TRUE)
    }.map { it.mapToTransportRoute() }
        .filter { if (transportType != null) it.transport.type == transportType else true }
}
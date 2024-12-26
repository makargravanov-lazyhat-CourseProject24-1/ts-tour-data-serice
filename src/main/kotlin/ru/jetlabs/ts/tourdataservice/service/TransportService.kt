package ru.jetlabs.ts.tourdataservice.service

import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.and
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.jetlabs.ts.tourdataservice.daos.TransportDao
import ru.jetlabs.ts.tourdataservice.models.Transport
import ru.jetlabs.ts.tourdataservice.models.enums.TransportType
import ru.jetlabs.ts.tourdataservice.models.mapToTransport
import ru.jetlabs.ts.tourdataservice.tables.Transports

@Component
@Transactional
class TransportService {
    fun getTransports(type: TransportType?, name: String?, contractorId: Long?): List<Transport> =
        TransportDao.find {
            (if (type != null) Transports.type eq type else Op.TRUE) and
                    (if (name != null) Transports.name like name else Op.TRUE) and
                    (if (contractorId != null) Transports.contractor eq contractorId else Op.TRUE)
        }.map { it.mapToTransport() }
}
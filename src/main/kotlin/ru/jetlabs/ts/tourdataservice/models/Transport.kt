package ru.jetlabs.ts.tourdataservice.models

import ru.jetlabs.ts.tourdataservice.daos.TransportDao
import ru.jetlabs.ts.tourdataservice.models.enums.TransportType

data class Transport(
    val id: Long,
    val type: TransportType,
    val name: String,
    val contractor: TransportContractor,
)

fun TransportDao.mapToTransport(): Transport = Transport(
    id = id.value,
    type = type,
    name = name,
    contractor = contractor.mapToTransportContractor()
)
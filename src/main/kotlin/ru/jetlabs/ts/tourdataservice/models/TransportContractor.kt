package ru.jetlabs.ts.tourdataservice.models

import ru.jetlabs.ts.tourdataservice.daos.TransportContractorDao

data class TransportContractor(
    val id: Long,
    val title: String,
    val description: String,
)

fun TransportContractorDao.mapToTransportContractor(): TransportContractor = TransportContractor(
    id = id.value,
    title = title,
    description = description
)
package ru.jetlabs.ts.tourdataservice.models

import ru.jetlabs.ts.tourdataservice.daos.PlaceDao

data class Place(
    val id: Long,
    val name: String,
    val address: String,
)

fun PlaceDao.mapToPlace(): Place =
    Place(
        id = id.value,
        name = name,
        address = address,
    )
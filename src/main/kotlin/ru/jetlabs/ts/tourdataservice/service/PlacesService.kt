package ru.jetlabs.ts.tourdataservice.service

import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.and
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.jetlabs.ts.tourdataservice.daos.PlaceDao
import ru.jetlabs.ts.tourdataservice.models.GetPlaceByIdResult
import ru.jetlabs.ts.tourdataservice.models.Place
import ru.jetlabs.ts.tourdataservice.models.mapToPlace
import ru.jetlabs.ts.tourdataservice.tables.Places

@Component
@Transactional
class PlacesService {
    fun getPlaces(name: String?, address: String?): List<Place> = PlaceDao.find {
        (if (name != null) Places.name like name else Op.TRUE) and (if (address != null) Places.address like address else Op.TRUE)
    }.map { it.mapToPlace() }

    fun getPlaceById(id: Long): GetPlaceByIdResult = PlaceDao.findById(id)?.mapToPlace()?.let {
        GetPlaceByIdResult.Success(it)
    } ?: GetPlaceByIdResult.NotFound
}
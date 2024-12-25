package ru.jetlabs.ts.tourdataservice.daos

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.jetlabs.ts.tourdataservice.tables.Places

class PlaceDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<PlaceDao>(Places)

    var name by Places.name
    var address by Places.address
}
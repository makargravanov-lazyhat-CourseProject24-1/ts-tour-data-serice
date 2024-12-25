package ru.jetlabs.ts.tourdataservice.daos

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.jetlabs.ts.tourdataservice.tables.Hotels

class HotelDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<HotelDao>(Hotels)

    var name by Hotels.name
    var level by Hotels.level
    var place by PlaceDao referencedOn Hotels.place
}


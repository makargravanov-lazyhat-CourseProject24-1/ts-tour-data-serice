package ru.jetlabs.ts.tourdataservice.daos

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.jetlabs.ts.tourdataservice.tables.HotelNutritions

class HotelNutritionDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<HotelNutritionDao>(HotelNutritions)

    var hotel by HotelDao referencedOn HotelNutritions.hotel
    var type by HotelNutritions.type
    val costPerDay by HotelNutritions.costPerDay
}
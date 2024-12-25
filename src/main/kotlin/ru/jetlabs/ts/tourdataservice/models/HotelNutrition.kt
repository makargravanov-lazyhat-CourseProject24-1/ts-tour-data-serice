package ru.jetlabs.ts.tourdataservice.models

import ru.jetlabs.ts.tourdataservice.daos.HotelNutritionDao
import ru.jetlabs.ts.tourdataservice.models.enums.NutritionType

data class HotelNutrition(
    val id: Long,
    val type: NutritionType,
    val costPerDay: Double
)

fun HotelNutritionDao.mapToHotelNutrition(): HotelNutrition = HotelNutrition(
    id = id.value,
    type = type,
    costPerDay = costPerDay
)
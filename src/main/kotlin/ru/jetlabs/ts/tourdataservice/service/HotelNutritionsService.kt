package ru.jetlabs.ts.tourdataservice.service

import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.and
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.jetlabs.ts.tourdataservice.daos.HotelNutritionDao
import ru.jetlabs.ts.tourdataservice.models.GetNutritionByIdResult
import ru.jetlabs.ts.tourdataservice.models.HotelNutrition
import ru.jetlabs.ts.tourdataservice.models.enums.NutritionType
import ru.jetlabs.ts.tourdataservice.models.mapToHotelNutrition
import ru.jetlabs.ts.tourdataservice.tables.HotelNutritions

@Component
@Transactional
class HotelNutritionsService {
    fun getHotelNutritions(
        type: NutritionType?, costPerDay: Double?
    ): List<HotelNutrition> = HotelNutritionDao.find {
        (if (type != null) HotelNutritions.type eq type else Op.TRUE) and (if (costPerDay != null) HotelNutritions.costPerDay eq costPerDay else Op.TRUE)
    }.map { it.mapToHotelNutrition() }

    fun getHotelNutritionById(id: Long): GetNutritionByIdResult =
        HotelNutritionDao.findById(id)?.mapToHotelNutrition()?.let {
            GetNutritionByIdResult.Success(it)
        } ?: GetNutritionByIdResult.NotFound
}
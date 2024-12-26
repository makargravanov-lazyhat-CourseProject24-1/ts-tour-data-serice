package ru.jetlabs.ts.tourdataservice.db

import org.jetbrains.exposed.sql.SchemaUtils
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.jetlabs.ts.tourdataservice.tables.*

@Component
@Transactional
class SchemaInitialize : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        SchemaUtils.create(
            Places,
            Hotels,
            HotelRooms,
            HotelNutritions,
            HotelBookings,
            RouteBookings,
            TransportRoutes,
            TransportContractors,
            Transports
        )
    }
}
package ru.jetlabs.ts.tourdataservice.daos

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.jetlabs.ts.tourdataservice.tables.Transports

class TransportDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<TransportDao>(Transports)

    var type by Transports.type
    var name by Transports.name
    var contractor by TransportContractorDao referencedOn Transports.contractor
}
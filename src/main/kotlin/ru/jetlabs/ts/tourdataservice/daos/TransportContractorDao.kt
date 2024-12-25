package ru.jetlabs.ts.tourdataservice.daos

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.jetlabs.ts.tourdataservice.tables.TransportContractors

class TransportContractorDao(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<TransportContractorDao>(TransportContractors)

    var title by TransportContractors.title
    var description by TransportContractors.description
}
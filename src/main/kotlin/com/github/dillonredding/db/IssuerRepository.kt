package com.github.dillonredding.db

import com.github.dillonredding.db.DatabaseFactory.dbQuery
import com.github.dillonredding.models.Issuer
import com.github.dillonredding.models.Issuers
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import java.util.*

class IssuerRepository {
    private fun ResultRow.toIssuer() = Issuer(
        id = this[Issuers.id],
        name = this[Issuers.name],
        description = this[Issuers.description],
        publicKey = this[Issuers.publicKey],
    )

    suspend fun findAll(): List<Issuer> = dbQuery {
        Issuers.selectAll().map { it.toIssuer() }
    }

    suspend fun findById(id: UUID): Issuer? = dbQuery {
        Issuers.select { Issuers.id eq id }
            .map { it.toIssuer() }
            .singleOrNull()
    }

    suspend fun save(name: String, publicKey: String, description: String? = null) = dbQuery {
        val insertStatement = Issuers.insert {
            it[Issuers.name] = name
            it[Issuers.description] = description
            it[Issuers.publicKey] = publicKey
        }
        insertStatement.resultedValues?.singleOrNull()?.toIssuer()
    }
}

val issuerRepository = IssuerRepository()

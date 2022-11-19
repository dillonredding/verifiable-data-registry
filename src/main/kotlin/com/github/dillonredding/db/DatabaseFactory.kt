package com.github.dillonredding.db

import com.github.dillonredding.models.Issuers
import kotlinx.coroutines.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*
import org.jetbrains.exposed.sql.transactions.experimental.*

object DatabaseFactory {
    fun init() {
        val driverClassName = "org.postgresql.Driver"
        val url = "jdbc:postgresql://localhost:5432/verifiable-data-registry?user=user&password=password"
        val database = Database.connect(url, driverClassName)
        transaction(database) {
            SchemaUtils.create(Issuers)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}
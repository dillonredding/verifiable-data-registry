package com.github.dillonredding.models

import com.github.dillonredding.common.UUIDSerializer
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import java.util.*

@Serializable
data class RegisterIssuerDto(
    val name: String,
    val description: String?,
    val publicKey: String
)

@Serializable
data class Issuer(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val name: String,
    val description: String? = null,
    val publicKey: String
)

val issuerStorage = mutableListOf<Issuer>()

object Issuers : Table() {
    val id = uuid("id").autoGenerate()
    val name = varchar("name", 128)
    val description = varchar("description", 1024).nullable()
    val publicKey = varchar("publicKey", 2048)

    override val primaryKey = PrimaryKey(id)
}

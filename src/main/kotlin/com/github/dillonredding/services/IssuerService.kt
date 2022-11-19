package com.github.dillonredding.services

import com.github.dillonredding.db.issuerRepository
import com.github.dillonredding.models.Issuer
import com.github.dillonredding.models.RegisterIssuerDto
import java.util.UUID

object IssuerService {
    suspend fun register(dto: RegisterIssuerDto) =
        issuerRepository.save(dto.name, dto.publicKey, dto.description)

    suspend fun findAll(): List<Issuer> =
        issuerRepository.findAll()

    suspend fun findById(id: UUID): Issuer? =
        issuerRepository.findById(id)

    suspend fun didDocFor(id: UUID) =
        this.findById(id)?.let {
            mapOf(
                "@context" to "https://www.w3.org/ns/did/v1",
                "id" to "did:web:localhost%3A8080:issuers:$id",
                "verificationMethod" to mapOf(
                    "id" to "",
                    "type" to "",
                    "controller" to "",
                    "publicKeyMultibase" to it.publicKey
                )
            )
        }
}
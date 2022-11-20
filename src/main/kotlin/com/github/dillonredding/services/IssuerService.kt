package com.github.dillonredding.services

import com.github.dillonredding.db.issuerRepository
import com.github.dillonredding.models.DidDoc
import com.github.dillonredding.models.Issuer
import com.github.dillonredding.models.RegisterIssuerDto
import com.github.dillonredding.models.VerificationMethod
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
            val did = "did:web:localhost%3A8080:issuers:$id"
            DidDoc(
                id = did,
                verificationMethods = listOf(VerificationMethod(
                    id = "$did#public-key",
                    type = "FooBar",
                    controller = did,
                    publicKeyMultibase = "-----BEGIN PUBLIC KEY-----foobar-----END PUBLIC KEY-----"
                ))
            )
        }
}
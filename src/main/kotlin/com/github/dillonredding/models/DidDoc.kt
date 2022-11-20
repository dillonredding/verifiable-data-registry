package com.github.dillonredding.models

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VerificationMethod(
    val id: String,
    val type: String,
    val controller: String,
    val publicKeyMultibase: String
)

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class DidDoc(
    @EncodeDefault
    @SerialName("@context")
    val context: List<String> = listOf(
        "https://www.w3.org/ns/did/v1",
        "https://w3id.org/security/suites/ed25519-2020/v1"
    ),
    val id: String,
    @SerialName("verificationMethod")
    val verificationMethods: List<VerificationMethod>,
)

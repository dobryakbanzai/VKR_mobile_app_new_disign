package com.example.vkr_new_disign.networkBlock

import kotlinx.serialization.Contextual
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder


import java.util.*

data class Token(val tokenVal: String)

data class TokenResponse(val token: Token)

object UUIDSerializer : KSerializer<UUID> {
    override val descriptor = PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): UUID {
        return UUID.fromString(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: UUID) {
        encoder.encodeString(value.toString())
    }
}

@Serializable
data class Student(
    @Contextual
    @Serializable(with = UUIDSerializer::class)
    @SerialName("id") val id: UUID?,

    @SerialName("firstname") val firstName: String,
    @SerialName("lastname") val lastname: String,

    @SerialName("personRole") val personRole: String,
    @SerialName("edClass") val edClass: Int,
    @SerialName("login") val login: String?,
    @SerialName("pass") val password: String?,

    @Contextual
    @Serializable(with = UUIDSerializer::class)
    @SerialName("teacherId") val teacherId: UUID?,

    @SerialName("aryProg") val aryProg: Int,
    @SerialName("derProg") val derProg: Int,
    @SerialName("tasksProg") val tasksProg: Int
) {
    constructor() : this(null, "", "", "",
                            0, "", "", null, 0, 0, 0
        )
}

@Serializable
data class Challange(
    @Contextual
    @Serializable(with = UUIDSerializer::class)
    @SerialName("id") val id: UUID?,

    @SerialName("challangeName") val challangeName: String,

    @SerialName("challangeType") val challangeType: String,

    @SerialName("challangeTarget") val challangeTarget: Int
)

@Serializable
data class Zad(
    @Contextual
    @Serializable(with = UUIDSerializer::class)
    @SerialName("id") val id: UUID?,

    @SerialName("taskText") val taskText: String,

    @SerialName("taskAnswer") val taskAnswer: String,


){
    constructor() : this(null, "", "")
}

//"taskText": "Сколько будет 6 + 4",
//        "taskAnswer": "10",
//        "studentAnswerChecks": [],
//        "tasksPackTasks": []

@Serializable
data class Teacher(
    @Contextual
    @Serializable(with = UUIDSerializer::class)
    @SerialName("id") val id: UUID?,

    @SerialName("firstname") val firstName: String,
    @SerialName("lastname") val lastname: String,

    @SerialName("personRole") val personRole: String,
    @SerialName("experience") val experience: Int,
    @SerialName("login") val login: String?,
    @SerialName("pass") val password: String?,
){
    constructor() : this(null, "", "", "",0, "", ""
    )
}

data class ZadCSV(val question: String, val answer: String)
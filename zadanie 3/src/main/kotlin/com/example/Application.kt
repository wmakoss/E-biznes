package com.example

import discord4j.core.DiscordClient
import discord4j.core.GatewayDiscordClient
import discord4j.core.event.domain.message.MessageCreateEvent
import discord4j.core.`object`.entity.Message
import reactor.core.publisher.Mono


fun main() {

    val kategorie = listOf("owoce", "warzywa", "slodycze", "produkty_mleczne")

    val mapaStringToListaStringow = mapOf(
        "owoce" to listOf("jablko", "banan", "gruszka", "sliwka"),
        "warzywa" to listOf("marchew", "ziemniak", "pomidor"),
        "slodycze" to listOf("czekolada", "cukierki", "batoniki"),
        "produkty_mleczne" to listOf("mleko", "kefir", "jogurt")
    )

    val token = "TOKEN"

    val client = DiscordClient.create(token)

    val login = client.withGateway { gateway: GatewayDiscordClient ->
        gateway.on<MessageCreateEvent, Any?>(
            MessageCreateEvent::class.java
        ) { event: MessageCreateEvent ->
            val message: Message = event.message
            if (!(!message.data.author().bot().isAbsent && message.data.author().bot().get())) {
                if(message.data.content() == "!hello") {
                    val response = "Hello " + message.data.author().username() + "!"
                    return@on message.getChannel()
                        .flatMap { channel -> channel.createMessage(response) }
                } else if(message.data.content() == "!help") {
                    val response = "HELP \n\ncommands: \n!help \n!hello \n!kategorie \n!kategoria <nazwa>"
                    return@on message.getChannel()
                        .flatMap { channel -> channel.createMessage(response) }
                } else if(message.data.content() == "!kategorie") {
                    val response = "Kategorie: " + kategorie.toString()
                    return@on message.getChannel()
                        .flatMap { channel -> channel.createMessage(response) }
                } else if(message.data.content().split(" ")[0] == "!kategoria") {
                    val response = "Kategoria " + message.data.content().split(" ")[1] + ": " + mapaStringToListaStringow.get(message.data.content().split(" ")[1]).toString()
                    return@on message.getChannel()
                        .flatMap { channel -> channel.createMessage(response) }
                }

            }
            Mono.empty<Any?>()
        }
    }

    login.block()
}

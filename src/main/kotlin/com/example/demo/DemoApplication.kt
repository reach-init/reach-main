package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}

@Entity
data class Message(@Id val id: String?, val text: String)

interface MessageRepository : CrudRepository<Message, String>{

}

@RestController
class MessageResource(val service: MessageRepository) {

	@PostMapping
	fun post(@RequestBody message: Message) {
		service.save(message)
	}
}
package com.reach.init

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam


@SpringBootApplication
class InitApplication

fun main(args: Array<String>) {
	runApplication<InitApplication>(*args)
}

@RestController
class Test(
	@Autowired
	val repo: CandidateRepository
) {
	@GetMapping("/")
	fun ceva(): User {
		return repo.insert(User("sosoaca", "trump"))
	}

	@GetMapping("/candidates/{id}")
	fun get(@PathVariable id : String ): User {
		return repo.findById(id).get()
	}

	@DeleteMapping("/candidates/{id}")
	fun delete(@PathVariable id : String ) {
		return repo.deleteById(id)
	}
	@GetMapping("/candidates")
	fun getAll(): List<User?> {
		return repo.findAll()
	}

	@PostMapping("/candidates")
	fun ceva2(@RequestBody candidate: User): User {
		return repo.insert(candidate)
	}
}

interface CandidateRepository : MongoRepository<User?, String?>

@Document(collection = "candidate")
data class User (
	@Id
	 val id: String? = null,
	@Indexed(unique = true)
	 val email: String? = null // getters and setters
)
package pl.edu.agh.eaiib.io.zsm.kotlindemo.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import pl.edu.agh.eaiib.io.zsm.kotlindemo.model.Student

@RepositoryRestResource(collectionResourceRel = "students", path = "students")
interface StudentsRepository : MongoRepository<Student, String>
package pl.edu.agh.eaiib.io.zsm.kotlindemo.model

import org.springframework.data.annotation.Id

data class Student(@Id val id: String = "",
                   val firstName: String = "",
                   val lastName: String = "",
                   val faculty: String = "",
                   val fieldOfStudy: String = "")
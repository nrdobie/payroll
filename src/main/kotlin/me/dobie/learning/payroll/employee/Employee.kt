package me.dobie.learning.payroll.employee

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Employee(
        var firstName: String,

        var lastName: String,

        var role: String,

        @Id @GeneratedValue var id: Long = 0) {

    var name: String
    get() = "$firstName $lastName"
    set(value) {
        val parts = value.split(" ", ignoreCase = true, limit =  1)

        firstName = parts.first()
        lastName = parts.last()
    }
}
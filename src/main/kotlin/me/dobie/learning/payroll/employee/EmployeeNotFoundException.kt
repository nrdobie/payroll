package me.dobie.learning.payroll.employee

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class EmployeeNotFoundException(id: Long) : RuntimeException("Unable to find employee #$id")
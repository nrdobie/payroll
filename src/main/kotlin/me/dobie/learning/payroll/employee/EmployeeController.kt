package me.dobie.learning.payroll.employee

import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.IanaLinkRelations
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class EmployeeController(
        private val repository: EmployeeRepository,
        private val assembler: EmployeeModelAssembler
) {
    @GetMapping("/employees")
    fun all(): CollectionModel<EntityModel<Employee>> {
        val employees = repository
                .findAll()

        return assembler.toCollectionModel(employees)
    }

    @PostMapping("/employees")
    fun newEmployee(@RequestBody newEmployee: Employee): ResponseEntity<EntityModel<Employee>> {
        val employee = assembler.toModel(repository.save(newEmployee))

        return ResponseEntity
                .created(employee.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(employee)

    }

    @GetMapping("/employees/{id}")
    fun one(@PathVariable id: Long): EntityModel<Employee> {
        val employee = repository.findById(id).orElseThrow { EmployeeNotFoundException(id) }

        return assembler.toModel(employee)
    }

    @PutMapping("/employees/{id}")
    fun replaceEmployee(@RequestBody newEmployee: Employee, @PathVariable id: Long): EntityModel<Employee> {
        val employee = repository
                .findById(id)
                .map { employee: Employee ->
                    employee.name = newEmployee.name
                    employee.role = newEmployee.role
                    repository.save(employee)
                }
                .orElseThrow { EmployeeNotFoundException(id) }

        return assembler.toModel(employee)
    }

    @DeleteMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteEmployee(@PathVariable id: Long) {
        repository.deleteById(id)
    }

}
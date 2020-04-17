package me.dobie.learning.payroll.employee

import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.RepresentationModelAssembler
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*
import org.springframework.stereotype.Component

@Component
class EmployeeModelAssembler: RepresentationModelAssembler<Employee, EntityModel<Employee>>  {
    override fun toModel(employee: Employee): EntityModel<Employee> {
        return EntityModel(employee,
                linkTo(methodOn(EmployeeController::class.java).one(employee.id)).withSelfRel(),
                linkTo(methodOn(EmployeeController::class.java).all()).withRel("employees"))
    }

    override fun toCollectionModel(entities: MutableIterable<Employee>): CollectionModel<EntityModel<Employee>> {
        return super.toCollectionModel(entities).add(
                linkTo(methodOn(EmployeeController::class.java).all()).withSelfRel())
    }
}
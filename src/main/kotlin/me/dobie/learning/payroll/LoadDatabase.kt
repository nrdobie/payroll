package me.dobie.learning.payroll

import me.dobie.learning.payroll.employee.Employee
import me.dobie.learning.payroll.employee.EmployeeRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LoadDatabase {
    companion object {
        private val log = LoggerFactory.getLogger(LoadDatabase::class.java)
    }

    @Bean
    fun initDatabase(repository: EmployeeRepository) = CommandLineRunner {
        log.info("Adding " + repository.save(Employee("Bilbo", "Baggins", "burglar")) + " to database.")
        log.info("Adding " + repository.save(Employee("Frodo", "Baggins", "thief")) + " to database.")
    }
}
package net.mysterymilktea.springbootbackend.repository;

import net.mysterymilktea.springbootbackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//JpaRepository<Employee,Long> Employee = name object and Long = primarykey
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}

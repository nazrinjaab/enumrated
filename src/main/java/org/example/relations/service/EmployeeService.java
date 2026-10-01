package org.example.relations.service;
import org.example.relations.entity.Employee;
import org.example.relations.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional(readOnly = true)
    public Employee getEmployee(Long id) {

        System.out.println("readonly");

        return employeeRepository.findById(id).orElse(null);
    }

    @Transactional
    public Employee saveEmployee(Employee employee) {

    return employeeRepository.save(employee);
    }
}

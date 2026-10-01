package org.example.relations.service;

import org.example.relations.entity.Department;
import org.example.relations.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    public Department getDepartment(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Transactional
    public void testLazy(Long id) {

        Department department = departmentRepository.findById(id).orElse(null);

        System.out.println("toxunmur");

        System.out.println("touches");

        System.out.println(department.getEmployees().size());

        System.out.println("load");
    }
}
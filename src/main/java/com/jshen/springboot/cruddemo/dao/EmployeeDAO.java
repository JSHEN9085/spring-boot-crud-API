package com.jshen.springboot.cruddemo.dao;

import com.jshen.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    public List<Employee> findAll();
}

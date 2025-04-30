package com.muchiri.bean;

import com.muchiri.dao.EmployeeDAO;
import com.muchiri.model.Employee;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.sql.SQLException;
import java.util.List;

@Named
@RequestScoped
public class EmployeeBean {
    private Employee employee;
    private List<Employee> employees;
    private final EmployeeDAO employeeDAO;
    
    public EmployeeBean() {
        employee = new Employee();
        employeeDAO = new EmployeeDAO();
    }
    
    @PostConstruct
    public void init() {
        try {
            employees = employeeDAO.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Employee getEmployee() {
        return employee;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public List<Employee> getEmployees() {
        return employees;
    }
} 
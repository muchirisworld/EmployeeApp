package com.muchiri.bean;

import com.muchiri.dao.EmployeeDAO;
import com.muchiri.model.Employee;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named
@SessionScoped
public class EmployeeBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Employee employee;
    private List<Employee> employees;
    private final EmployeeDAO employeeDAO;
    
    public EmployeeBean() {
        employee = new Employee();
        employeeDAO = new EmployeeDAO();
    }
    
    @PostConstruct
    public void init() {
        loadEmployees();
    }
    
    private void loadEmployees() {
        try {
            employees = employeeDAO.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void save() {
        try {
            employeeDAO.create(employee);    
            loadEmployees();
            employee = new Employee();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void edit(Employee emp) {
        this.employee = emp;
    }
    
    public void delete(Long id) {
        try {
            employeeDAO.delete(id);
            loadEmployees();
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
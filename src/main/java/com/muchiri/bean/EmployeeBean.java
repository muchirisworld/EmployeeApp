package com.muchiri.bean;

import com.muchiri.dao.EmployeeDAO;
import com.muchiri.model.Employee;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
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
            addErrorMessage("Error loading employees: " + e.getMessage());
        }
    }
    
    public String edit(Long id) {
        try {
            employee = employeeDAO.findById(id);
            if (employee == null) {
                addErrorMessage("Employee not found");
                return "index";
            }
            return "edit";
        } catch (SQLException e) {
            addErrorMessage("Error loading employee: " + e.getMessage());
            return "index";
        }
    }
    
    public String update() {
        try {
            employeeDAO.update(employee);
            addSuccessMessage("Employee updated successfully");
            loadEmployees();
            return "index";
        } catch (SQLException e) {
            addErrorMessage("Error updating employee: " + e.getMessage());
            return null;
        }
    }
    
    public void save() {
        try {
            employeeDAO.create(employee);    
            addSuccessMessage("Employee created successfully");
            loadEmployees();
            employee = new Employee();
        } catch (SQLException e) {
            addErrorMessage("Error creating employee: " + e.getMessage());
        }
    }
    
    public void delete(Long id) {
        try {
            employeeDAO.delete(id);
            addSuccessMessage("Employee deleted successfully");
            loadEmployees();
        } catch (SQLException e) {
            addErrorMessage("Error deleting employee: " + e.getMessage());
        }
    }
    
    private void addSuccessMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", message));
    }
    
    private void addErrorMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", message));
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
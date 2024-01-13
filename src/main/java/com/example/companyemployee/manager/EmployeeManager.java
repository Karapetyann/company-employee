package com.example.companyemployee.manager;

import com.example.companyemployee.db.DBConnectionProvider;
import com.example.companyemployee.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {

    Connection connection = DBConnectionProvider.getInstance().getConnection();
    CompanyManager companyManager = new CompanyManager();

    public List<Employee> getAll() {
        String sql = "SELECT * FROM employee";
        List<Employee> employees = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                employees.add(Employee.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .company(companyManager.getCompanyById(resultSet.getInt("company_id")))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    public void add(Employee employee) {
        String sql = "INSERT INTO employee(name, surname, email,company_id) VALUES(?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getSurname());
            ps.setString(3, employee.getEmail());
            ps.setInt(4, employee.getCompany().getId());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int anInt = generatedKeys.getInt(1);
                employee.setId(anInt);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM employee WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Employee> getByCompanyId(int companyId) {
        String sql = "SELECT * FROM employee WHERE company_id = " + companyId;
        List<Employee> employees = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                employees.add(Employee.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .company(companyManager.getCompanyById(resultSet.getInt("company_id")))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }
}

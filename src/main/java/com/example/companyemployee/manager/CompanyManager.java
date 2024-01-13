package com.example.companyemployee.manager;

import com.example.companyemployee.db.DBConnectionProvider;
import com.example.companyemployee.model.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyManager {

    Connection connection = DBConnectionProvider.getInstance().getConnection();

    public List<Company> getCompanies() {
        String sql = "SELECT * FROM company";
        List<Company> companies = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                companies.add(Company.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .address(resultSet.getString("address"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }

    public Company getCompanyById(int id) {
        String sql = "SELECT * FROM company WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
               return Company.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .address(resultSet.getString("address"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void add(Company company) {
        String sql = "INSERT INTO company(name, address) VALUES(?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, company.getName());
            ps.setString(2, company.getAddress());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int anInt = generatedKeys.getInt(1);
                company.setId(anInt);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void delete(int id) {
        String sql = "DELETE FROM company WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

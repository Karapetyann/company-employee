package com.example.companyemployee.servlet;

import com.example.companyemployee.manager.CompanyManager;
import com.example.companyemployee.manager.EmployeeManager;
import com.example.companyemployee.model.Company;
import com.example.companyemployee.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/addEmployee")

public class AddEmployeeServlet extends HttpServlet {
    EmployeeManager employeeManager = new EmployeeManager();
    CompanyManager companyManager = new CompanyManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Company> companies = companyManager.getCompanies();
        req.setAttribute("companies", companies);
        req.getRequestDispatcher("/WEB-INF/addEmployee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int companyId = Integer.parseInt(req.getParameter("company_id"));
        Company companyById = companyManager.getCompanyById(companyId);
        employeeManager.add(Employee.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .company(companyById)
                .build());
        resp.sendRedirect("/employees");
    }
}

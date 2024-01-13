package com.example.companyemployee.servlet;

import com.example.companyemployee.manager.CompanyManager;
import com.example.companyemployee.model.Company;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/addCompany")

public class AddCompanyServlet extends HttpServlet {
    CompanyManager companyManager = new CompanyManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/addCompany.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String companyName = req.getParameter("companyName");
        String companyAddress = req.getParameter("companyAddress");
        companyManager.add(Company.builder()
                .name(companyName)
                .address(companyAddress)
                .build());
        resp.sendRedirect("/companies");
    }
}

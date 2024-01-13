package com.example.companyemployee.servlet;

import com.example.companyemployee.manager.CompanyManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteCompany")

public class DeleteCompanyServlet extends HttpServlet {

    CompanyManager companyManager = new CompanyManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id =Integer.parseInt(req.getParameter("id"));
        companyManager.delete(id);
        resp.sendRedirect("/companies");
    }
}

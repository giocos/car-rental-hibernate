package it.project.carRental.SI2001.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static it.project.carRental.SI2001.constants.MessageEnum.SESSION_ERROR;

@WebServlet("/account/*")
public class AccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            if (request.getSession().getAttribute("username") != null) {
                response.sendRedirect("account.jsp");
            } else {
                throw new RuntimeException(SESSION_ERROR.getMessage());
            }
        } catch (final Exception e) {
            response.sendError(501, e.getMessage());
        }
    }
}


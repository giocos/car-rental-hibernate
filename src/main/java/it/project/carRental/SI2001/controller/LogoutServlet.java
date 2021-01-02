package it.project.carRental.SI2001.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static it.project.carRental.SI2001.constants.MessageEnum.SESSION_ERROR;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            final HttpSession session = request.getSession();
            if (session.getAttribute("username") == null) {
                throw new RuntimeException(SESSION_ERROR.getMessage());
            }
            session.invalidate();
            response.sendRedirect("index.jsp");

        } catch (final Exception e) {
            response.sendError(404, e.getMessage());
        }
    }
}

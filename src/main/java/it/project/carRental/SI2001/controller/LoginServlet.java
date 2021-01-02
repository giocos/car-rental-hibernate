package it.project.carRental.SI2001.controller;

import it.project.carRental.SI2001.entity.User;
import it.project.carRental.SI2001.repository.AbstractUserDao;
import it.project.carRental.SI2001.repository.impl.UserDaoImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static it.project.carRental.SI2001.constants.MessageEnum.LOGIN_ERROR;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            final String username = request.getParameter("username");
            final String password = request.getParameter("password");

            final AbstractUserDao<User, Integer> userDaoImpl = new UserDaoImpl();

            if (userDaoImpl.isLoggedOn(username, password)) {

                final User user = userDaoImpl.findByUsername(username);
                final HttpSession session = request.getSession();

                session.setAttribute("userId", user.getId());
                session.setAttribute("username", user.getUsername());
                session.setAttribute("role", user.getRole().getRole());
                session.setMaxInactiveInterval(Integer.MAX_VALUE);
                
                response.sendRedirect("home");

            } else {
                throw new RuntimeException(LOGIN_ERROR.getMessage());
            }
        } catch (final Exception e) {
            response.sendError(404, e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("index.jsp");
    }
}

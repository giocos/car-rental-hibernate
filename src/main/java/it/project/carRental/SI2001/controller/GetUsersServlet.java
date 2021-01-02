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
import java.util.List;

import static it.project.carRental.SI2001.constants.MessageEnum.PERMISSION_DENIED;

@WebServlet("/all/users")
public class GetUsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            final HttpSession session = request.getSession();
            if (session.getAttribute("username") != null) {
                final AbstractUserDao<User, Integer> userDaoImpl = new UserDaoImpl();
                final List<User> users = userDaoImpl.findAll();

                session.setAttribute("users", users);
                response.sendRedirect("home.jsp");

            } else {
                throw new RuntimeException(PERMISSION_DENIED.getMessage());
            }

        } catch (final Exception e) {
            response.sendError(404, e.getMessage());
        }
    }
}

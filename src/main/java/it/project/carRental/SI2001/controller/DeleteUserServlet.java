package it.project.carRental.SI2001.controller;

import it.project.carRental.SI2001.constants.MessageEnum;
import it.project.carRental.SI2001.entity.User;
import it.project.carRental.SI2001.repository.AbstractUserDao;
import it.project.carRental.SI2001.repository.impl.UserDaoImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete/user")
public class DeleteUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            if (request.getSession().getAttribute("username") != null) {
                final String id = request.getParameter("id");
                final AbstractUserDao<User, Integer> userDaoImpl = new UserDaoImpl();
                final User user = userDaoImpl.findByPrimaryKey(Integer.valueOf(id));

                userDaoImpl.delete(user);

                response.sendRedirect("home");

            } else {
                throw new RuntimeException(MessageEnum.SESSION_ERROR.getMessage());
            }

        } catch (final Exception e) {
            response.sendError(404, e.getMessage());
        }
    }
}

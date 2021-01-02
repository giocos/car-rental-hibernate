package it.project.carRental.SI2001.controller;

import it.project.carRental.SI2001.entity.Rental;
import it.project.carRental.SI2001.entity.User;
import it.project.carRental.SI2001.facade.CarFacade;
import it.project.carRental.SI2001.repository.AbstractDao;
import it.project.carRental.SI2001.repository.AbstractUserDao;
import it.project.carRental.SI2001.repository.impl.RentalDaoImpl;
import it.project.carRental.SI2001.repository.impl.UserDaoImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            final HttpSession session = request.getSession();
            if (session.getAttribute("role").toString().equalsIgnoreCase("admin")) {
                final AbstractUserDao<User, Integer> userDaoImpl = new UserDaoImpl();
                final List<User> users = userDaoImpl.findAll();
                session.setAttribute("users", users);

            } else {
                final AbstractDao<Rental, Integer> rentalDaoImpl = new RentalDaoImpl();
                final List<Rental> rentals = rentalDaoImpl.findAllByPrimaryKey(
                        Integer.valueOf(session.getAttribute("userId").toString()));
                session.setAttribute("rentals", rentals);

                CarFacade.setAvailableCars(session);
            }
            response.sendRedirect("home.jsp");

        } catch (final Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

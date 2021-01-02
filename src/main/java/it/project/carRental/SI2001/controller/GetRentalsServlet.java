package it.project.carRental.SI2001.controller;

import it.project.carRental.SI2001.entity.Rental;
import it.project.carRental.SI2001.repository.AbstractDao;
import it.project.carRental.SI2001.repository.impl.RentalDaoImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static it.project.carRental.SI2001.constants.MessageEnum.PERMISSION_DENIED;

@WebServlet("/all/rentals")
public class GetRentalsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            final HttpSession session = request.getSession();
            if (session.getAttribute("username") != null) {
                final String userId = session.getAttribute("userId").toString();
                final int id = Integer.parseInt(userId);

                final AbstractDao<Rental, Integer> rentalDaoImpl = new RentalDaoImpl();
                final List<Rental> rentals = rentalDaoImpl.findAllByPrimaryKey(id);

                session.setAttribute("rentals", rentals);
                response.sendRedirect("home.jsp");

            } else {
                throw new RuntimeException(PERMISSION_DENIED.getMessage());
            }

        } catch (final Exception e){
            response.sendError(404, e.getMessage());
        }
    }
}

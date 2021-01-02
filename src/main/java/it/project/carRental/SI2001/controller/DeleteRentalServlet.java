package it.project.carRental.SI2001.controller;

import it.project.carRental.SI2001.entity.Rental;
import it.project.carRental.SI2001.repository.AbstractDao;
import it.project.carRental.SI2001.repository.impl.RentalDaoImpl;
import it.project.carRental.SI2001.utils.TimeDifferenceUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static it.project.carRental.SI2001.constants.MessageEnum.INVALID_RENTAL;
import static it.project.carRental.SI2001.constants.MessageEnum.SESSION_ERROR;

@WebServlet("/delete/rental")
public class DeleteRentalServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            if (request.getSession().getAttribute("username") != null) {
                final String id = request.getParameter("id");
                final AbstractDao<Rental, Integer> rentalDaoImpl = new RentalDaoImpl();
                final Rental rental = rentalDaoImpl.findByPrimaryKey(Integer.valueOf(id));

                if (TimeDifferenceUtil.difference(rental.getRentalDate().toString()) >= 2) {
                    rentalDaoImpl.delete(rental);
                    response.sendRedirect("home");

                } else {
                    throw new RuntimeException(SESSION_ERROR.getMessage());
                }

            } else {
                throw new RuntimeException(INVALID_RENTAL.getMessage());
            }
        } catch (final Exception e) {
            response.sendError(404, e.getMessage());
        }
    }
}

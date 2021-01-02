package it.project.carRental.SI2001.controller;

import it.project.carRental.SI2001.entity.Car;
import it.project.carRental.SI2001.entity.Rental;
import it.project.carRental.SI2001.facade.CarFacade;
import it.project.carRental.SI2001.repository.AbstractCarDao;
import it.project.carRental.SI2001.repository.AbstractDao;
import it.project.carRental.SI2001.repository.impl.CarDaoImpl;
import it.project.carRental.SI2001.repository.impl.RentalDaoImpl;
import it.project.carRental.SI2001.utils.TimeDifferenceUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static it.project.carRental.SI2001.constants.MessageEnum.INVALID_RENTAL;

@WebServlet("/updateRental")
public class UpdateRentalServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final PrintWriter out = response.getWriter();
        try {
            final HttpSession session = request.getSession();
            final String id = request.getParameterValues("values[]")[0];
            //final String car = request.getParameterValues("values[]")[1];
            final String model = request.getParameterValues("values[]")[2];
            final AbstractDao<Rental, Integer> rentalDaoImpl = new RentalDaoImpl();
            final Rental rental = rentalDaoImpl.findByPrimaryKey(Integer.valueOf(id));

            if (TimeDifferenceUtil.difference(rental.getRentalDate().toString()) >= 2) {
                final AbstractCarDao<Car, String> carDaoImpl = new CarDaoImpl();
                rental.setCar(carDaoImpl.findByModel(model));
                rentalDaoImpl.saveOrUpdate(rental);

                CarFacade.setUpdateFired(true);
                CarFacade.setAvailableCars(session);

                out.println(rental.getCar().getPlateNumber());

            } else {
                throw new RuntimeException(INVALID_RENTAL.getMessage());
            }

        } catch (final Exception e) {
            response.setStatus(404);
            out.println(e.getMessage());

        } finally {
            out.flush();
            out.close();
        }
    }
}

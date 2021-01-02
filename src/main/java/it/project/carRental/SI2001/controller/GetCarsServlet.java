package it.project.carRental.SI2001.controller;

import it.project.carRental.SI2001.entity.Car;
import it.project.carRental.SI2001.repository.AbstractCarDao;
import it.project.carRental.SI2001.repository.impl.CarDaoImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static it.project.carRental.SI2001.constants.MessageEnum.PERMISSION_DENIED;

@WebServlet("/all/cars")
public class GetCarsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            final HttpSession session = request.getSession();
            if (session.getAttribute("username") != null) {
                final AbstractCarDao<Car, String> carDaoImpl = new CarDaoImpl();
                final List<Car> cars = carDaoImpl.findAll();

                session.setAttribute("cars", cars);
                response.sendRedirect("car-park.jsp");

            } else {
                throw new RuntimeException(PERMISSION_DENIED.getMessage());
            }
        } catch (final Exception e) {
            response.sendError(404, e.getMessage());
        }
    }
}

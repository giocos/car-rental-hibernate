package it.project.carRental.SI2001.controller;

import it.project.carRental.SI2001.entity.Fine;
import it.project.carRental.SI2001.entity.Rental;
import it.project.carRental.SI2001.repository.AbstractDao;
import it.project.carRental.SI2001.repository.impl.FineDaoImpl;
import it.project.carRental.SI2001.repository.impl.RentalDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static it.project.carRental.SI2001.constants.MessageEnum.PERMISSION_DENIED;

@WebServlet("/create/fine")
public class CreateFineServlet extends HttpServlet {

    private static final String SEMICOLUMN = ";";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final PrintWriter out = response.getWriter();
        try {
            final String[] values = request.getParameterValues("values");

            final Fine fine = new Fine();
            fine.setType(values[0]);
            fine.setAmount(Integer.parseInt(values[1]));

            final AbstractDao<Rental, Integer> rentalDaoImpl = new RentalDaoImpl();
            final Rental rental = rentalDaoImpl.findByPrimaryKey(Integer.valueOf(values[2]));
            fine.setRental(rental);

            final AbstractDao<Fine, Integer> fineDaoImpl = new FineDaoImpl();
            fineDaoImpl.saveOrUpdate(fine);

            final StringBuilder responseBuilder = new StringBuilder();
            buildResponse(responseBuilder, fine);
            //response
            out.println(responseBuilder.toString());

        } catch (final Exception e) {
            System.err.println(e.getMessage());

        } finally {
            out.flush();
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            final HttpSession session = request.getSession();
            if (session.getAttribute("username") != null) {
                if (session.getAttribute("role").toString().equals("admin")) {
                    response.sendRedirect("street-fine.jsp");
                }
            }
        } catch (final Exception e) {
            response.sendError(404, PERMISSION_DENIED.getMessage());
        }
    }

    private void buildResponse(final StringBuilder response, final Fine fine) {
        if (fine != null) {
            response.append(fine.getId()).append(SEMICOLUMN)
                    .append(fine.getAmount()).append(SEMICOLUMN)
                    .append(fine.getRental().getId()).append(SEMICOLUMN)
                    .append(fine.getRental().getCar().getManufacturer()).append(SEMICOLUMN)
                    .append(fine.getRental().getCar().getModel()).append(SEMICOLUMN);
        }
    }
}

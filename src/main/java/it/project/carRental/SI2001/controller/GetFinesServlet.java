package it.project.carRental.SI2001.controller;

import it.project.carRental.SI2001.entity.Fine;
import it.project.carRental.SI2001.repository.AbstractDao;
import it.project.carRental.SI2001.repository.impl.FineDaoImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static it.project.carRental.SI2001.constants.MessageEnum.PERMISSION_DENIED;

@WebServlet("/all/fines")
public class GetFinesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            final HttpSession session = request.getSession();
            if (session.getAttribute("username") != null) {
                List<Fine> fines = null;
                final AbstractDao<Fine, Integer> fineDaoImpl = new FineDaoImpl();

                if( session.getAttribute("role").toString().equals("customer")) {
                    final String userId = session.getAttribute("userId").toString();
                    fines = fineDaoImpl.findAllByPrimaryKey(Integer.valueOf(userId));

                } else {
                    fines = fineDaoImpl.findAll();
                }
                session.setAttribute("fines", fines);
                response.sendRedirect("street-fine.jsp");

            } else {
                throw new RuntimeException(PERMISSION_DENIED.getMessage());
            }
        } catch (final Exception e) {
            response.sendError(404, e.getMessage());
        }
    }
}

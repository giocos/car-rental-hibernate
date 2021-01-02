package it.project.carRental.SI2001.controller;

import it.project.carRental.SI2001.entity.Coupon;
import it.project.carRental.SI2001.repository.AbstractDao;
import it.project.carRental.SI2001.repository.impl.CouponDaoImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static it.project.carRental.SI2001.constants.MessageEnum.PERMISSION_DENIED;

@WebServlet("/all/coupons")
public class GetCouponsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            final HttpSession session = request.getSession();
            if (session.getAttribute("username") != null) {
                final AbstractDao<Coupon, Integer> couponDaoORM = new CouponDaoImpl();
                final String userId = session.getAttribute("userId").toString();
                final List<Coupon> coupons = couponDaoORM.findAllByPrimaryKey(Integer.valueOf(userId));

                if (!coupons.isEmpty()) {
                    session.setAttribute("coupon", coupons);
                }
                response.sendRedirect("street-fine.jsp");

            } else {
                throw new RuntimeException(PERMISSION_DENIED.getMessage());
            }

        } catch (final Exception e) {
            response.sendError(501, e.getMessage());
        }
    }
}

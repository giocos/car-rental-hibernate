package it.project.carRental.SI2001.controller;

import it.project.carRental.SI2001.entity.Car;
import it.project.carRental.SI2001.entity.Coupon;
import it.project.carRental.SI2001.entity.Rental;
import it.project.carRental.SI2001.entity.User;
import it.project.carRental.SI2001.repository.AbstractCarDao;
import it.project.carRental.SI2001.repository.AbstractDao;
import it.project.carRental.SI2001.repository.AbstractUserDao;
import it.project.carRental.SI2001.repository.CrudRepository;
import it.project.carRental.SI2001.repository.impl.CarDaoImpl;
import it.project.carRental.SI2001.repository.impl.CouponDaoImpl;
import it.project.carRental.SI2001.repository.impl.RentalDaoImpl;
import it.project.carRental.SI2001.repository.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import static it.project.carRental.SI2001.constants.MessageEnum.SESSION_ERROR;

@WebServlet("/add/rental")
public class AddRentalServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
        final String model = request.getParameter("model").toString();
        final CrudRepository<Rental, Integer> rentalDaoImpl = new RentalDaoImpl();
        final Rental rental = createRental(userId, model);
        rentalDaoImpl.saveOrUpdate(rental);

        System.out.println(rental.getCar().getModel() + "\n"
            + rental.getCar().getManufacturer() + "\n"
                + rental.getCoupon().getAmount());

        response.sendRedirect("getRentals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("username") != null) {
            response.sendRedirect("rental.jsp");
        } else {
            response.sendError(404, SESSION_ERROR.getMessage());
        }
    }

    private Rental createRental(final int id, final String model) {
        Rental rental = null;
        try {
            rental = new Rental();
            rental.setRentalDate(new Date());
            //save coupon
            final AbstractDao<Coupon, Integer> couponDaoImpl = new CouponDaoImpl();
            final Coupon coupon = new Coupon();
            coupon.setAmount(new Random().nextInt(250) + 50);
            couponDaoImpl.saveOrUpdate(coupon);
            //find user
            final AbstractUserDao<User, Integer> userDaoImpl = new UserDaoImpl();
            final User user = userDaoImpl.findByPrimaryKey(id);
            //find car
            final AbstractCarDao<Car, String> carDaoImpl = new CarDaoImpl();
            final Car car = carDaoImpl.findByModel(model);

            rental.setCoupon(coupon);
            rental.setUser(user);
            rental.setCar(car);

        } catch (final Exception e) {
            e.printStackTrace();
        }
        return rental;
    }
}

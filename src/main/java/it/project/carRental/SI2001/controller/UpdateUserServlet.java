package it.project.carRental.SI2001.controller;

import it.project.carRental.SI2001.entity.User;
import it.project.carRental.SI2001.repository.AbstractUserDao;
import it.project.carRental.SI2001.repository.impl.UserDaoImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/update/user")
public class UpdateUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            final String id = request.getParameterValues("values[]")[0];
            final AbstractUserDao<User, Integer> userDaoImpl = new UserDaoImpl();

            final User user = userDaoImpl.findByPrimaryKey(Integer.valueOf(id));
            user.setFirstname(request.getParameterValues("values[]")[1]);
            user.setLastname(request.getParameterValues("values[]")[2]);

            //replace char '-' with '/'
            final String date = request.getParameterValues("values[]")[3].replaceAll("-", "/");
            user.setBirthDate(new SimpleDateFormat("yyyy/MM/dd").parse(date));
            user.setUsername(request.getParameterValues("values[]")[4]);
            userDaoImpl.saveOrUpdate(user);

            //uptade users inside HttpSession
            final List<User> users = userDaoImpl.findAll();
            request.getSession().setAttribute("users", users);
            request.getSession().setAttribute("modified", "false");

        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}

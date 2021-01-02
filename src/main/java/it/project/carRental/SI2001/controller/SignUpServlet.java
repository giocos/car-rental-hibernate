package it.project.carRental.SI2001.controller;

import it.project.carRental.SI2001.entity.Role;
import it.project.carRental.SI2001.entity.User;
import it.project.carRental.SI2001.repository.AbstractUserDao;
import it.project.carRental.SI2001.repository.CrudRepository;
import it.project.carRental.SI2001.repository.impl.RoleDaoImpl;
import it.project.carRental.SI2001.repository.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

    private static final String PATTERN = "yyyy/MM/dd";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            final AbstractUserDao<User, Integer> userDaoImpl = new UserDaoImpl();
            final CrudRepository<Role, String> roleDaoImpl = new RoleDaoImpl();

            final Role role = new Role(request.getParameterValues("values")[4]);
            roleDaoImpl.saveOrUpdate(role);

            final User user = createUser(request.getParameterValues("values"), role);
            userDaoImpl.saveOrUpdate(user);

            response.sendRedirect("getUsers");

        } catch (final Exception e) {
            response.sendError(404, e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("sign-up.jsp");
    }

    private User createUser(final String[] args, final Role role) {
        User user = null;
        try {
            user = new User();

            user.setFirstname(args[0]);
            user.setLastname(args[1]);
            user.setUsername(args[3]);
            user.setPassword(args[5]);
            user.setRole(role);
            //parsing data
            final String data = args[2].replaceAll("\\-", "\\/");
            user.setBirthDate(new SimpleDateFormat(PATTERN).parse(data));

        } catch (final Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}

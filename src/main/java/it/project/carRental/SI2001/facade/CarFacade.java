package it.project.carRental.SI2001.facade;

import it.project.carRental.SI2001.entity.Car;
import it.project.carRental.SI2001.repository.AbstractCarDao;
import it.project.carRental.SI2001.repository.impl.CarDaoImpl;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class CarFacade {

    private static boolean updateFired = false;
    private static Map<String, String> modelManufacturer;

    protected CarFacade() {}

    /**
     * Method is called only if an update is done for Entity {@link Car}
     * @param session
     */
    public static void setAvailableCars(final HttpSession session) {
        final AbstractCarDao<Car, String> carDaoImpl = new CarDaoImpl();
        final List<Car> availableCars = carDaoImpl.findAll();

        if (modelManufacturer == null) {
            setSessionAttributes(availableCars, session);
        }

        if (availableCars.size() != modelManufacturer.size() || updateFired) {
            setSessionAttributes(availableCars, session);
            updateFired = false;
        }
    }

    private static void setSessionAttributes(final List<Car> cars, HttpSession session) {
        modelManufacturer = cars.stream().collect(
                Collectors.toMap(Car::getModel, Car::getManufacturer));

        session.setAttribute("cars", modelManufacturer.values());
        session.setAttribute("models", modelManufacturer.keySet());
    }

    public static void setUpdateFired(boolean updateFired) {
        CarFacade.updateFired = updateFired;
    }
}

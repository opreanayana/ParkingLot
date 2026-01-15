package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.CarDto;
import com.parking.parkinglot.entities.Car;
import com.parking.parkinglot.entities.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CarsBean {

    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    public List<CarDto> findAllCars() {
        LOG.info("findAllCars");
        try {
            TypedQuery<Car> typedQuery =
                    entityManager.createQuery("SELECT c FROM Car c", Car.class);

            List<Car> cars = typedQuery.getResultList();
            return copyCarsToDto(cars);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void createCar(String licensePlate, String parkingSpot, Long userId) {
        LOG.info("createCar");

        try {
            Car car = new Car();
            car.setLicensePlate(licensePlate);
            car.setParkingSpot(parkingSpot);

            User user = entityManager.find(User.class, userId);
            if (user == null) {
                throw new EJBException("User not found for id=" + userId);
            }

            user.getCars().add(car);
            car.setOwner(user);

            entityManager.persist(car);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public CarDto findById(Long carId) {
        LOG.info("findById");

        try {
            Car car = entityManager.find(Car.class, carId);
            if (car == null) {
                return null;
            }

            String ownerName =
                    (car.getOwner() != null) ? car.getOwner().getUsername() : null;

            return new CarDto(
                    car.getId(),
                    car.getLicensePlate(),
                    car.getParkingSpot(),
                    ownerName
            );
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void updateCar(Long carId, String licensePlate, String parkingSpot, Long userId) {
        LOG.info("updateCar");

        try {
            Car car = entityManager.find(Car.class, carId);
            if (car == null) {
                throw new EJBException("Car not found for id=" + carId);
            }

            car.setLicensePlate(licensePlate);
            car.setParkingSpot(parkingSpot);

            User oldUser = car.getOwner();
            if (oldUser != null && oldUser.getCars() != null) {
                oldUser.getCars().remove(car);
            }

            User user = entityManager.find(User.class, userId);
            if (user == null) {
                throw new EJBException("User not found for id=" + userId);
            }

            user.getCars().add(car);
            car.setOwner(user);

        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void deleteCarsByIds(java.util.Collection<Long> carIds) {
        LOG.info("deleteCarsByIds");

        try {
            for (Long carId : carIds) {
                Car car = entityManager.find(Car.class, carId);
                if (car != null) {
                    entityManager.remove(car);
                }
            }
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }


    private List<CarDto> copyCarsToDto(List<Car> cars) {
        List<CarDto> dtos = new ArrayList<>();
        if (cars == null) {
            return dtos;
        }

        for (Car car : cars) {
            String ownerName =
                    (car.getOwner() != null) ? car.getOwner().getUsername() : null;

            CarDto dto = new CarDto(
                    car.getId(),
                    car.getLicensePlate(),
                    car.getParkingSpot(),
                    ownerName
            );

            dtos.add(dto);
        }

        return dtos;
    }
}

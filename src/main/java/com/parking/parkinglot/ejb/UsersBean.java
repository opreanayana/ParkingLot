package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.UserDto;
import com.parking.parkinglot.entities.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class UsersBean {

    @PersistenceContext
    private EntityManager entityManager;

    public List<UserDto> findAllUsers() {
        List<User> users = entityManager
                .createQuery("SELECT u FROM User u", User.class)
                .getResultList();

        return users.stream()
                .map(u -> new UserDto(u.getUsername(), u.getEmail()))
                .collect(Collectors.toList());
    }
}

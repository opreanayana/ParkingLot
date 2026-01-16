package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.UserDto;
import com.parking.parkinglot.entities.User;
import com.parking.parkinglot.entities.UserGroup;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.logging.Logger;

@Stateless
public class UsersBean {

    private static final Logger LOG =
            Logger.getLogger(UsersBean.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private PasswordBean passwordBean;


    public List<UserDto> findAllUsers() {
        List<User> users = entityManager
                .createQuery("SELECT u FROM User u", User.class)
                .getResultList();

        return users.stream()
                .map(u -> new UserDto(u.getUsername(), u.getEmail()))
                .collect(Collectors.toList());
    }

    public List<User> findAllUserEntities() {
        return entityManager
                .createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }


    public void createUser(String username,
                           String email,
                           String password,
                           Collection<String> groups) {

        LOG.info("createUser");

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(passwordBean.convertToSha256(password));

        entityManager.persist(newUser);

        assignGroupsToUser(username, groups);
    }

    private void assignGroupsToUser(String username,
                                    Collection<String> groups) {

        LOG.info("assignGroupsToUser");

        for (String group : groups) {
            UserGroup userGroup = new UserGroup();
            userGroup.setUsername(username);
            userGroup.setUserGroup(group);
            entityManager.persist(userGroup);
        }
    }
}

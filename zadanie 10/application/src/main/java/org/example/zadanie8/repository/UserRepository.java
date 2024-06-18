package org.example.zadanie8.repository;

import org.example.zadanie8.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT u FROM User u")
    List<User> getAll();

    @Query("SELECT u FROM User u WHERE u.id = :id")
    List<User> getAllById(@Param("id") Integer id);

    @Query("SELECT u.id FROM User u WHERE u.username = :username")
    List<Integer> getAllIdByUsername(@Param("username") String username);

    @Query("SELECT u.id FROM User u WHERE u.mail = :mail")
    List<Integer> getAllIdByMail(@Param("mail") String mail);

    @Query("SELECT u.id FROM User u WHERE u.username = :username AND u.password = :password")
    List<Integer> getAllIdByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}

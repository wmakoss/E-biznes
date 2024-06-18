package org.example.zadanie8.service;

import com.google.common.hash.Hashing;
import org.example.zadanie8.entity.Session;
import org.example.zadanie8.entity.User;
import org.example.zadanie8.repository.SessionRepository;
import org.example.zadanie8.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SessionRepository sessionRepository;

    private String sha256(String input) {
        return Hashing.sha256().hashString(input, StandardCharsets.UTF_8).toString();
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>(userRepository.getAll());
//        System.out.println(users.size());
        return users;
    }

    public String addNewUser(String username, String password, String password2, String mail) {

        if (!password.equals(password2)) {
            return "Podane hasla nie sa takie same";
        }

        if (username.length() < 3 || username.length() > 20) {
            return "Login powinien miec dlugosc od 3 do 20 znakow";
        }

        if (ifUsernameExists(username)) {
            return "Podana nazwa uzytkownika jest juz zajeta";
        }

        if (ifMailExists(mail)) {
            return "Podany adres email jest juz zajety";
        }

        userRepository.save(new User(username, sha256(password), mail));

        return "";
    }

    private Boolean ifUsernameExists(String username) {
        List<Integer> ids = new ArrayList<>(userRepository.getAllIdByUsername(username));
        if (!ids.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean ifMailExists(String mail) {
        List<Integer> ids = new ArrayList<>(userRepository.getAllIdByMail(mail));
        if (!ids.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public String login(String username, String password) {

        List<Integer> ids = new ArrayList<>(userRepository.getAllIdByUsernameAndPassword(username, sha256(password)));

        if (ids.size() != 1) {
            return "";
        }

        Integer id = ids.get(0);

        return createSession(id);

    }

    private String createSession(Integer userId) {
        LocalDateTime expirationDatetime = LocalDateTime.now().plusDays(1);
        String uuid = UUID.randomUUID().toString();

        sessionRepository.save(new Session(uuid, userId, expirationDatetime.toString()));

        return uuid;
    }

    public Integer checkSession(String uuid) {
        List<Session> sessions = sessionRepository.findByUuid(uuid);
        if (sessions.size() == 1) {

            Session sesssion = sessions.get(0);

            if (LocalDateTime.parse(sesssion.getExpirationDatetime()).isAfter(LocalDateTime.now())) {
                return sesssion.getUserId();
            } else {
                return -1;
            }

        } else {
            return -1;
        }

    }

    public String getUsernameById(Integer id) {
        List<User> users = new ArrayList<>(userRepository.getAllById(id));

        if (users.size() != 1) {
            return "";
        } else {
            return users.get(0).getUsername();
        }
    }

}

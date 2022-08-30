package edu.school21.cinema.services;

import edu.school21.cinema.models.UserSession;
import edu.school21.cinema.repositories.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSessionServiceImpl implements UserSessionService {

    private UserSessionRepository userSessionRepository;

    @Autowired
    public void setUserSessionRepository(UserSessionRepository userSessionRepository) {
        this.userSessionRepository = userSessionRepository;
    }

    public List<UserSession> findAll() {
        List<UserSession> userSessions = new ArrayList<>();
        userSessionRepository.findAll().forEach(userSessions::add);
        return userSessions;
    }

    public boolean add(UserSession entity) {
        userSessionRepository.save(entity);
        return true;
    }

    public void delete(UserSession entity) {
        userSessionRepository.delete(entity);
    }

    public void update(UserSession entity) {
        userSessionRepository.save(entity);
    }

    public List<UserSession> getAllByUserId(Long id) {
        return userSessionRepository.getAllByUserId(id);
    }
}

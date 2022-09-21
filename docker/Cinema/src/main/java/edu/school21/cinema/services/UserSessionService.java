package edu.school21.cinema.services;

import edu.school21.cinema.models.UserSession;

import java.util.List;

public interface UserSessionService extends Service<UserSession> {
    List<UserSession> getAllByUserId(Long id);
}

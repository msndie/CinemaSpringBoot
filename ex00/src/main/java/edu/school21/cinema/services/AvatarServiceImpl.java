package edu.school21.cinema.services;

import edu.school21.cinema.models.Avatar;
import edu.school21.cinema.repositories.AvatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AvatarServiceImpl implements AvatarService {

    private AvatarRepository avatarRepository;

    @Autowired
    public void setAvatarRepository(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    public List<Avatar> findAll() {
        List<Avatar> avatars = new ArrayList<>();
        avatarRepository.findAll().forEach(avatars::add);
        return avatars;
    }

    public boolean add(Avatar entity) {
        avatarRepository.save(entity);
        return true;
    }

    public void delete(Avatar entity) {
        avatarRepository.delete(entity);
    }

    public void update(Avatar entity) {
        avatarRepository.save(entity);
    }

    public List<Avatar> findAllByUserId(Long id) {
        return avatarRepository.findAllByUserId(id);
    }

    public Optional<Avatar> findByUuid(UUID uuid) {
        return avatarRepository.getByUuid(uuid);
    }
}

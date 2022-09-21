package edu.school21.cinema.services;

import edu.school21.cinema.models.Image;
import edu.school21.cinema.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    private ImageRepository imageRepository;

    @Autowired
    public void setImageRepository(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Optional<Image> findByUuid(UUID uuid) {
        return imageRepository.findByUuid(uuid);
    }

    public List<Image> findAllByUserId(Long userId) {
        return imageRepository.findAllByUserId(userId);
    }

    public List<Image> findAll() {
        List<Image> list = new ArrayList<>();
        imageRepository.findAll().forEach(list::add);
        return list;
    }

    public boolean add(Image entity) {
        imageRepository.save(entity);
        return true;
    }

    public void delete(Image entity) {
        imageRepository.delete(entity);
    }

    public void update(Image entity) {
        imageRepository.save(entity);
    }
}

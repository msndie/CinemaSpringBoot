package edu.school21.cinema.models;

import edu.school21.cinema.utils.Utils;

import java.util.UUID;

public class Image {
    private Long id;
    private Long user_id;
    private UUID uuid;
    private String name;
    private String mime;
    private long size;

    public Image(Long user_id, UUID uuid, String name, String mime, long size) {
        this.user_id = user_id;
        this.uuid = uuid;
        this.name = name;
        this.mime = mime;
        this.size = size;
        this.id = null;
    }

    public Image() {
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", uuid=" + uuid +
                ", name='" + name + '\'' +
                ", mime='" + mime + '\'' +
                ", size='" + size + '\'' +
                '}';
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getSizeInStr() {
        return Utils.convert(size);
    }
}

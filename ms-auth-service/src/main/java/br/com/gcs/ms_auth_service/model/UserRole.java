package br.com.gcs.ms_auth_service.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

@Table
@Entity(name = "tb_users_roles")
public class UserRole implements Serializable {

    @EmbeddedId
    private UserRoleId id;

    private LocalDateTime createdAt;

    public UserRoleId getId() {
        return id;
    }

    public void setId(UserRoleId id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @PrePersist
    public void prePersist() {
        setCreatedAt(LocalDateTime.now());
    }

}

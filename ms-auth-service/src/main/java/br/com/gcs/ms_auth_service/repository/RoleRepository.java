package br.com.gcs.ms_auth_service.repository;

import br.com.gcs.ms_auth_service.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByDescription(String description);
}

package br.com.gcs.ms_auth_service.repository;

import br.com.gcs.ms_auth_service.model.UserRole;
import br.com.gcs.ms_auth_service.model.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
}

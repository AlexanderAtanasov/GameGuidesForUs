package com.example.gameGuidesForUs.repository;

import com.example.gameGuidesForUs.model.entity.UserRoleEntity;
import com.example.gameGuidesForUs.model.entity.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    UserRoleEntity findByRole(UserRoleEnum user);
}

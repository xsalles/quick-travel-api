package br.com.quick_travel.main.modules.User.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.quick_travel.main.modules.User.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    boolean existsByEmail(String email);

    UserModel findByEmail(String email);
} 

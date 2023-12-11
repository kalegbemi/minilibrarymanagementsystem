package com.alexxycode.minilibrarymanagementsystem.repository;

import com.alexxycode.minilibrarymanagementsystem.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    List<Users> findByFullName(String fullName);

    Optional<Users> findByEmail(String email);
}

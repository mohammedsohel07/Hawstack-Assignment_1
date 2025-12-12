package com.example.assignment.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.assignment.model.User;
public interface UserRepository extends JpaRepository<User,Long>{}

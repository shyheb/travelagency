package com.ditraacademy.travelagency.core;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> { // Integer lel id mta3 User type int
}

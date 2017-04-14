package com.spring.data.jpa.Repository;

import com.spring.data.jpa.Entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserData, Long> {
    UserData findById(Long id);
}

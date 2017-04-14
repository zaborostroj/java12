package com.spring.data.jpa.Repository;

import com.spring.data.jpa.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PersonRepository extends JpaRepository<Person,Long>{
    List<Person> findById(Long id);
}

package com.example.authentication.repository;

import com.example.authentication.model.Foo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FooRepository extends JpaRepository<Foo, Integer> {

}

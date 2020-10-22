package com.topyougo.productimport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.topyougo.productimport.model.JNTAccounts;

@Repository
public interface JNTRepository extends JpaRepository<JNTAccounts, Integer> {

}

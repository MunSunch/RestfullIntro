package com.munsun.hateoas_spring.hateoasspring.repository;

import com.munsun.hateoas_spring.hateoasspring.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {}
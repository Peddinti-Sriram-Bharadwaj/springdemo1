package com.sriram9217.demo1.repo;

import com.sriram9217.demo1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {



}

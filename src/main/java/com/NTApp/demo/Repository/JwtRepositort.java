package com.NTApp.demo.Repository;

import com.NTApp.demo.Models.Jwt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface JwtRepositort extends JpaRepository<Jwt,Long> {

}

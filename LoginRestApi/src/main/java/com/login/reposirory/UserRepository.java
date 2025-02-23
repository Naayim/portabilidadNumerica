package com.login.reposirory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.login.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(String username);
    Optional<User> findById(Integer id);
    
    @Modifying()
    @Query("update User u set u.firstname=:firstname, u.lastname=:lastname where u.id = :id")
    void updateUser(@Param(value = "id") Long id,   @Param(value = "firstname") String firstname, @Param(value = "lastname") String lastname);

    


}

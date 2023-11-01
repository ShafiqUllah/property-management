package edu.miu.apsd.olpe.repository;

import edu.miu.apsd.olpe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}

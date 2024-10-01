package fr.hb.icicafaitduspringavecboot.repository;

import fr.hb.icicafaitduspringavecboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User,String> {

    @Query("SELECT u FROM User u ORDER BY RAND() LIMIT 1")
    User getOneRandom();

    Optional<User> findByEmail(String email);

	Optional<User> findByActivationToken(String activationToken);
}

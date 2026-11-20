package software.mexdi.portfolio.Contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
	boolean existsByEmail(String email);

	@Query("SELECT COUNT(c) FROM Contact c WHERE c.email = :email AND c.createdAt >= :startOfDay")
	int countByEmailAndCreatedAtAfter(@Param("email") String email, @Param("startOfDay") LocalDateTime startOfDay);

	List<Contact> findByEmail(String email);
}

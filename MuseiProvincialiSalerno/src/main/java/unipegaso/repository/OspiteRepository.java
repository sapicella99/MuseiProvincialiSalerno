package unipegaso.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import unipegaso.model.Ospite;

public interface OspiteRepository extends JpaRepository<Ospite, Integer> {
	Optional<Ospite> findByEmail(String email);
}

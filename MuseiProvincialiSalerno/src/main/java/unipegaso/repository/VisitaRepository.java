package unipegaso.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import unipegaso.model.Visita;


public interface VisitaRepository extends JpaRepository<Visita, Integer> {

	List<Visita> findByMuseoIdMuseo(Integer idMuseo);
	
	List<Visita> findByOspiteIdOspite(Integer idOspite);
	
	List<Visita> findByDataVisita(LocalDate dataVisita);
	
	Optional<Visita> findByCodicePrenotazione(String codicePrenotazione);
	
	@Query("""
		       SELECT COALESCE(SUM(v.numOspiti),0)
		       FROM Visita v
		       WHERE v.museo.idMuseo = :idMuseo
		       AND v.dataVisita = :dataVisita
		       AND v.statoVisita <> 'ANNULLATA'
		       """)
		Integer totaleOspitiPrenotati(
		        @Param("idMuseo") Integer idMuseo,
		        @Param("dataVisita") LocalDate dataVisita
		);
}

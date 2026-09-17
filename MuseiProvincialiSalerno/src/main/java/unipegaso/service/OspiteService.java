package unipegaso.service;

import java.util.List;

import org.springframework.stereotype.Service;

import unipegaso.model.Ospite;
import unipegaso.repository.OspiteRepository;

@Service
public class OspiteService {
	private final OspiteRepository ospiteRepository;

	public OspiteService(OspiteRepository ospiteRepository) {
		this.ospiteRepository = ospiteRepository;
	}

	public List<Ospite> getAllOspiti() {

		return ospiteRepository.findAll();
	}

	public Ospite getOspiteById(Integer id) {

		return ospiteRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Ospite non trovato con id: " + id));
	}

	public Ospite saveOspite(Ospite ospite) {

		return ospiteRepository.save(ospite);
	}

	public void deleteOspite(Integer id) {

		ospiteRepository.deleteById(id);
	}

}

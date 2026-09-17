package unipegaso.service;

import java.util.List;

import org.springframework.stereotype.Service;

import unipegaso.model.Museo;
import unipegaso.repository.MuseoRepository;

@Service
public class MuseoService {

	private final MuseoRepository museoRepository;

	public MuseoService(MuseoRepository museoRepository) {
		this.museoRepository = museoRepository;
	}

	public List<Museo> getAllMusei() {
		return museoRepository.findAll();
	}

	public Museo getMuseoById(Integer id) {
		return museoRepository.findById(id).orElse(null);
	}

	public Museo saveMuseo(Museo museo) {
		return museoRepository.save(museo);
	}

	public void deleteMuseo(Integer id) {
		museoRepository.deleteById(id);
	}
}

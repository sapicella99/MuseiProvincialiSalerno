package unipegaso.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import unipegaso.model.Museo;
import unipegaso.service.MuseoService;

@RestController
@RequestMapping("/api/musei")
@CrossOrigin(origins = "*")
public class MuseoController {

	private final MuseoService museoService;

	public MuseoController(MuseoService museoService) {
		this.museoService = museoService;
	}

	@GetMapping
	public List<Museo> getAllMusei() {

		return museoService.getAllMusei();
	}

	@GetMapping("/{id}")
	public Museo getMuseoById(@PathVariable Integer id) {

		return museoService.getMuseoById(id);
	}

	@PostMapping
	public Museo createMuseo(@RequestBody Museo museo) {

		return museoService.saveMuseo(museo);
	}

	@PutMapping("/{id}")
	public Museo updateMuseo(@PathVariable Integer id, @RequestBody Museo museo) {

		museo.setIdMuseo(id);

		return museoService.saveMuseo(museo);
	}

	@DeleteMapping("/{id}")
	public void deleteMuseo(@PathVariable Integer id) {

		museoService.deleteMuseo(id);
	}
}


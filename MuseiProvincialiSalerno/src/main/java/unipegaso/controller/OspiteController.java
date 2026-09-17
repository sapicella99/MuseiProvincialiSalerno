package unipegaso.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import unipegaso.model.Ospite;
import unipegaso.service.OspiteService;

@RestController
@RequestMapping("/api/ospiti")
@CrossOrigin(origins = "*")
public class OspiteController {

	private final OspiteService ospiteService;

	public OspiteController(OspiteService ospiteService) {
		this.ospiteService = ospiteService;
	}

	@GetMapping
	public List<Ospite> getAllOspiti() {

		return ospiteService.getAllOspiti();
	}

	@GetMapping("/{id}")
	public Ospite getOspiteById(@PathVariable Integer id) {

		return ospiteService.getOspiteById(id);
	}

	@PostMapping
	public Ospite createOspite(@RequestBody Ospite ospite) {

		return ospiteService.saveOspite(ospite);
	}

	@PutMapping("/{id}")
	public Ospite updateOspite(@PathVariable Integer id, @RequestBody Ospite ospite) {

		Ospite ospiteEsistente = ospiteService.getOspiteById(id);

		ospiteEsistente.setNome(ospite.getNome());
		ospiteEsistente.setCognome(ospite.getCognome());
		ospiteEsistente.setEmail(ospite.getEmail());
		ospiteEsistente.setTelefono(ospite.getTelefono());

		return ospiteService.saveOspite(ospiteEsistente);
	}

	@DeleteMapping("/{id}")
	public void deleteOspite(@PathVariable Integer id) {

		ospiteService.deleteOspite(id);
	}
}

package unipegaso.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import unipegaso.model.Visita;
import unipegaso.service.VisitaService;

@RestController
@RequestMapping("/api/visite")
@CrossOrigin(origins = "*")
public class VisitaController {

	private final VisitaService visitaService;

	public VisitaController(VisitaService visitaService) {

		this.visitaService = visitaService;

	}

	@GetMapping
	public List<Visita> getAllVisite() {

		return visitaService.getAllVisite();

	}

	@GetMapping("/{id}")
	public Visita getVisitaById(@PathVariable Integer id) {

		return visitaService.getVisitaById(id);

	}

	@PostMapping
	public Visita creaVisita(@RequestBody Visita visita) {

		return visitaService.creaVisita(visita);

	}

	@PutMapping("/{id}")
	public Visita aggiornaVisita(@PathVariable Integer id, @RequestBody Visita visita) {

		return visitaService.aggiornaVisita(id, visita);

	}

	@DeleteMapping("/{id}")
	public void eliminaVisita(@PathVariable Integer id) {

		visitaService.eliminaVisita(id);

	}

	@GetMapping("/museo/{idMuseo}")
	public List<Visita> trovaPerMuseo(@PathVariable Integer idMuseo) {

		return visitaService.trovaVisitePerMuseo(idMuseo);

	}

	@GetMapping("/ospite/{idOspite}")
	public List<Visita> trovaPerOspite(@PathVariable Integer idOspite) {

		return visitaService.trovaVisitePerOspite(idOspite);

	}

	@GetMapping("/codice/{codice}")
	public Visita cercaPerCodice(@PathVariable String codice) {

		return visitaService.cercaPerCodice(codice);

	}

	@GetMapping("/data/{data}")
	public List<Visita> trovaPerData(@PathVariable LocalDate data) {

		return visitaService.trovaVisitePerData(data);

	}

	@PutMapping("/{id}/conferma")
	public Visita confermaVisita(@PathVariable("id") Integer id) {

		return visitaService.confermaVisita(id);

	}

	@PutMapping("/{id}/annulla")
	public Visita annullaVisita(@PathVariable("id") Integer id) {

		return visitaService.annullaVisita(id);

	}

}

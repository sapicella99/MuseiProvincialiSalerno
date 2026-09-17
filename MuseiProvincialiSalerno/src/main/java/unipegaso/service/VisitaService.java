package unipegaso.service;

import java.util.UUID;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import unipegaso.model.Museo;
import unipegaso.model.Ospite;
import unipegaso.model.Visita;
import unipegaso.model.enums.StatoVisita;
import unipegaso.repository.MuseoRepository;
import unipegaso.repository.OspiteRepository;
import unipegaso.repository.VisitaRepository;

@Service
public class VisitaService {

	private final VisitaRepository visitaRepository;
	private final MuseoRepository museoRepository;
	private final OspiteRepository ospiteRepository;

	public VisitaService(VisitaRepository visitaRepository, MuseoRepository museoRepository,
			OspiteRepository ospiteRepository) {

		this.visitaRepository = visitaRepository;
		this.museoRepository = museoRepository;
		this.ospiteRepository = ospiteRepository;
	}

	public List<Visita> getAllVisite() {

		return visitaRepository.findAll();

	}

	public Visita getVisitaById(Integer id) {

		return visitaRepository.findById(id).orElseThrow(() -> new RuntimeException("Visita non trovata"));

	}

	public Visita creaVisita(Visita visita) {

		// Controllo numero ospiti

		if (visita.getNumOspiti() == null || visita.getNumOspiti() <= 0) {

			throw new IllegalArgumentException("Il numero degli ospiti deve essere maggiore di zero");
		}

		// Controllo data visita

		if (visita.getDataVisita().isBefore(LocalDate.now())) {

			throw new IllegalArgumentException("La data della visita non può essere nel passato");
		}

		// Controllo capienza museo

		Integer idMuseo = visita.getMuseo().getIdMuseo();

		Museo museo = museoRepository.findById(idMuseo).orElseThrow(() -> new RuntimeException("Museo non trovato"));

		Integer ospitiGiaPrenotati = visitaRepository.totaleOspitiPrenotati(idMuseo, visita.getDataVisita());

		if (ospitiGiaPrenotati == null) {
			ospitiGiaPrenotati = 0;
		}

		if (ospitiGiaPrenotati + visita.getNumOspiti() > museo.getCapienza()) {

			throw new IllegalArgumentException("Posti disponibili insufficienti per questo museo");

		}

		LocalTime oraVisita = visita.getOraVisita();

		LocalTime apertura = LocalTime.parse(museo.getOraApertura());

		LocalTime chiusura = LocalTime.parse(museo.getOraChiusura());

		if (oraVisita.isBefore(apertura) || oraVisita.isAfter(chiusura)) {

			throw new IllegalArgumentException("L'orario della visita è fuori dall'orario di apertura del museo");

		}

		// Controllo se l'ospite esiste già tramite email

		Ospite ospite = ospiteRepository.findByEmail(visita.getOspite().getEmail()).orElse(null);

		// Se non esiste lo creo

		if (ospite == null) {

			ospite = new Ospite();

			ospite.setNome(visita.getOspite().getNome());
			ospite.setCognome(visita.getOspite().getCognome());
			ospite.setEmail(visita.getOspite().getEmail());
			ospite.setTelefono(visita.getOspite().getTelefono());

			ospite = ospiteRepository.save(ospite);

		}

		// Associo museo e ospite alla visita

		visita.setMuseo(museo);
		visita.setOspite(ospite);
		// Campi automatici

		visita.setDataPrenotazione(LocalDateTime.now());

		visita.setCodicePrenotazione(generaCodicePrenotazione());

		visita.setStatoVisita(StatoVisita.CONFERMATA);

		return visitaRepository.save(visita);

	}

	public Visita cercaPerCodice(String codice) {

		return visitaRepository.findByCodicePrenotazione(codice)
				.orElseThrow(() -> new RuntimeException("Prenotazione non trovata."));

	}

	public List<Visita> trovaVisitePerMuseo(Integer idMuseo) {

		return visitaRepository.findByMuseoIdMuseo(idMuseo);

	}

	public List<Visita> trovaVisitePerOspite(Integer idOspite) {

		return visitaRepository.findByOspiteIdOspite(idOspite);

	}

	public List<Visita> trovaVisitePerData(LocalDate data) {

		return visitaRepository.findByDataVisita(data);

	}

	public void eliminaVisita(Integer id) {

		visitaRepository.deleteById(id);

	}

	public Visita aggiornaVisita(Integer id, Visita nuovaVisita) {

		Visita visitaEsistente = getVisitaById(id);

		visitaEsistente.setDataVisita(nuovaVisita.getDataVisita());

		visitaEsistente.setOraVisita(nuovaVisita.getOraVisita());

		visitaEsistente.setNumOspiti(nuovaVisita.getNumOspiti());

		return visitaRepository.save(visitaEsistente);

	}

	public Visita confermaVisita(Integer id) {

		Visita visita = getVisitaById(id);

		visita.setStatoVisita(StatoVisita.CONFERMATA);

		return visitaRepository.save(visita);

	}

	public Visita annullaVisita(Integer id) {

		Visita visita = getVisitaById(id);

		visita.setStatoVisita(StatoVisita.ANNULLATA);

		return visitaRepository.save(visita);

	}

	private String generaCodicePrenotazione() {

		return "MPS-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();

	}
}

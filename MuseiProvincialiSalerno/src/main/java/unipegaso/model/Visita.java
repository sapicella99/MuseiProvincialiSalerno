package unipegaso.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import unipegaso.model.enums.StatoVisita;
import jakarta.persistence.*;

@Entity
@Table(name = "visita")
public class Visita {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_visita")
    private Integer idVisita;


    @Column(name = "data_prenotazione")
    private LocalDateTime dataPrenotazione;


    @Column(name = "data_visita")
    private LocalDate dataVisita;


    @Column(name = "ora_visita")
    private LocalTime oraVisita;


    @Column(name = "num_ospiti")
    private Integer numOspiti;

    @Enumerated(EnumType.STRING)
    @Column(name = "stato_visita")
    private StatoVisita statoVisita;
    
    @Column(name = "codice_prenotazione", unique = true)
    private String codicePrenotazione;



    @ManyToOne
    @JoinColumn(name = "id_ospite")
    private Ospite ospite;



    @ManyToOne
    @JoinColumn(name = "id_museo")
    private Museo museo;


    public Visita() {

    }


	public Integer getIdVisita() {
		return idVisita;
	}


	public void setIdVisita(Integer idVisita) {
		this.idVisita = idVisita;
	}


	public LocalDateTime getDataPrenotazione() {
		return dataPrenotazione;
	}


	public void setDataPrenotazione(LocalDateTime dataPrenotazione) {
		this.dataPrenotazione = dataPrenotazione;
	}


	public LocalDate getDataVisita() {
		return dataVisita;
	}


	public void setDataVisita(LocalDate dataVisita) {
		this.dataVisita = dataVisita;
	}


	public LocalTime getOraVisita() {
		return oraVisita;
	}


	public void setOraVisita(LocalTime oraVisita) {
		this.oraVisita = oraVisita;
	}


	public Integer getNumOspiti() {
		return numOspiti;
	}


	public void setNumOspiti(Integer numOspiti) {
		this.numOspiti = numOspiti;
	}





	public StatoVisita getStatoVisita() {
		return statoVisita;
	}


	public void setStatoVisita(StatoVisita statoVisita) {
		this.statoVisita = statoVisita;
	}


	public Ospite getOspite() {
		return ospite;
	}


	public void setOspite(Ospite ospite) {
		this.ospite = ospite;
	}


	public Museo getMuseo() {
		return museo;
	}


	public void setMuseo(Museo museo) {
		this.museo = museo;
	}
	
	public String getCodicePrenotazione() {
	    return codicePrenotazione;
	}

	public void setCodicePrenotazione(String codicePrenotazione) {
	    this.codicePrenotazione = codicePrenotazione;
	}
    
    
}

package unipegaso.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Museo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMuseo;

	private String nomeMuseo;

	private String descrizione;

	private String indirizzo;

	private String Comune;

	private String email;

	private String numTelefono;

	private String oraApertura;

	private String oraChiusura;

	private Integer capienza;

	private String foto;

	public Museo() {

	}

	public Integer getIdMuseo() {
		return idMuseo;
	}

	public void setIdMuseo(Integer idMuseo) {
		this.idMuseo = idMuseo;
	}

	public String getNomeMuseo() {
		return nomeMuseo;
	}

	public void setNomeMuseo(String nomeMuseo) {
		this.nomeMuseo = nomeMuseo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getComune() {
		return Comune;
	}

	public void setComune(String comune) {
		Comune = comune;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(String numTelefono) {
		this.numTelefono = numTelefono;
	}

	public String getOraApertura() {
		return oraApertura;
	}

	public void setOraApertura(String oraApertura) {
		this.oraApertura = oraApertura;
	}

	public String getOraChiusura() {
		return oraChiusura;
	}

	public void setOraChiusura(String oraChiusura) {
		this.oraChiusura = oraChiusura;
	}

	public Integer getCapienza() {
		return capienza;
	}

	public void setCapienza(Integer capienza) {
		this.capienza = capienza;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

}

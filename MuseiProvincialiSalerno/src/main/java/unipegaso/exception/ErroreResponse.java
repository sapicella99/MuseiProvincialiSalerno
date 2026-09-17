package unipegaso.exception;

public class ErroreResponse {

	private String messaggio;

	public ErroreResponse(String messaggio) {
		this.messaggio = messaggio;
	}

	public String getMessaggio() {
		return messaggio;
	}

	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}

}

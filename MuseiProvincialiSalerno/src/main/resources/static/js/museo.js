const params = new URLSearchParams(window.location.search);

const idMuseo = params.get("id");

fetch(`/api/musei/${idMuseo}`)

.then(r => r.json())

.then(museo => {

document.getElementById("contenutoMuseo").innerHTML = `

<div class="card shadow-lg">

<img
src="img/${museo.foto}"
class="card-img-top"
style="max-height:500px; object-fit:cover;">

<div class="card-body">

<h1>${museo.nomeMuseo}</h1>

<hr>

<p>${museo.descrizione}</p>

<p><strong>📍 Indirizzo:</strong> ${museo.indirizzo}</p>

<p><strong>🏙 Comune:</strong> ${museo.comune}</p>

<p><strong>📧 Email:</strong> ${museo.email}</p>

<p><strong>☎ Telefono:</strong> ${museo.numTelefono}</p>

<p><strong>🕘 Apertura:</strong> ${museo.oraApertura}</p>

<p><strong>🕕 Chiusura:</strong> ${museo.oraChiusura}</p>

<p><strong>👥 Capienza:</strong> ${museo.capienza}</p>

<a
href="prenotazione.html?id=${museo.idMuseo}"
class="btn btn-warning btn-lg">

Prenota visita

</a>

</div>

</div>

`;

});
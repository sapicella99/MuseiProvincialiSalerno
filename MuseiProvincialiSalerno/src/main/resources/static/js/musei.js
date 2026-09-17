let tuttiMusei = [];

fetch("/api/musei")
    .then(response => response.json())
    .then(musei => {

        tuttiMusei = musei;

        mostraMusei(musei);

    });

function mostraMusei(musei) {

    const listaMusei = document.getElementById("listaMusei");

    listaMusei.innerHTML = "";

    musei.forEach(museo => {

        listaMusei.innerHTML += `

<div class="col-lg-4 col-md-6 mb-4">

<div class="card h-100 shadow">

<img
src="img/${museo.foto}"
class="card-img-top"
style="height:250px; object-fit:cover;">


<div class="card-body d-flex flex-column">


<h4>
${museo.nomeMuseo}
</h4>


<div class="descrizione-museo">

<p>
${museo.descrizione}
</p>

</div>


<hr>


<div class="info-museo mt-auto">


<p>
<strong>📍 Comune:</strong> ${museo.comune}
</p>


<p>
<strong>🏠 Indirizzo:</strong> ${museo.indirizzo}
</p>


<p>
<strong>🕘 Orario:</strong> ${museo.oraApertura} - ${museo.oraChiusura}
</p>


</div>


</div>


<div class="card-footer text-center">

<a
href="prenotazione.html?id=${museo.idMuseo}"
class="btn btn-warning">

Prenota

</a>

</div>


</div>

</div>

`;

    });

}
document.getElementById("ricercaMuseo")
.addEventListener("input", function () {

    const testo = this.value.toLowerCase();

    const filtrati = tuttiMusei.filter(museo =>

        museo.nomeMuseo.toLowerCase().includes(testo) ||

        museo.comune.toLowerCase().includes(testo)

    );

    mostraMusei(filtrati);

});
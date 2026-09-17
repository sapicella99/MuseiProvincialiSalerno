const params = new URLSearchParams(window.location.search);

const idMuseo = params.get("id");


let museoSelezionato;



if (idMuseo != null) {

    fetch(`/api/musei/${idMuseo}`)

    .then(response => response.json())

    .then(museo => {

        museoSelezionato = museo;

        document.getElementById("nomeMuseo").innerHTML =
            museo.nomeMuseo;

    });

}
else {

    // Mostro la select
    document.getElementById("contenitoreMusei").style.display = "block";

    fetch("/api/musei")

    .then(response => response.json())

    .then(musei => {

        const select = document.getElementById("selectMuseo");

        select.innerHTML = "";

        musei.forEach(museo => {

            select.innerHTML += `
                <option value="${museo.idMuseo}">
                    ${museo.nomeMuseo}
                </option>
            `;

        });

        museoSelezionato = musei[0];
        document.getElementById("nomeMuseo").innerHTML =
            museoSelezionato.nomeMuseo;

        select.addEventListener("change", function () {

            museoSelezionato = musei.find(m =>
                m.idMuseo == this.value
            );

            document.getElementById("nomeMuseo").innerHTML =
                museoSelezionato.nomeMuseo;

        });

    });

}





document.getElementById("formPrenotazione").addEventListener("submit", function (event) {

    event.preventDefault();

    const visita = {

    dataVisita: document.getElementById("dataVisita").value,

    oraVisita: document.getElementById("oraVisita").value + ":00",

    numOspiti: parseInt(document.getElementById("numOspiti").value),

    museo: {

        idMuseo: museoSelezionato.idMuseo

    },

    ospite: {

        nome: document.getElementById("nome").value,

        cognome: document.getElementById("cognome").value,

        email: document.getElementById("email").value,

        telefono: document.getElementById("telefono").value

    }

};


    fetch("/api/visite", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(visita)

    })

    .then(response => {

        if (!response.ok) {

    return response.json().then(errore => {

        throw new Error(errore.messaggio);

    });

}

        return response.json();

    })

    .then(visitaCreata => {
        console.log(visitaCreata);

        sessionStorage.setItem("ultimaPrenotazione",
            JSON.stringify(visitaCreata));

        window.location.href = "conferma.html";

    })

    .catch(error => {

        document.getElementById("messaggio").innerHTML =

        `<div class="alert alert-danger">

            ${error.message}

        </div>`;

    });

});
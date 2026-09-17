document.getElementById("btnCarica").addEventListener("click", () => {

    const codice = document.getElementById("codicePrenotazione").value.trim();

    if (codice === "") {

        alert("Inserisci il codice della prenotazione.");

        return;

    }

    fetch(`/api/visite/codice/${encodeURIComponent(codice)}`)

        .then(response => {

            if (!response.ok) {

                throw new Error("Prenotazione non trovata.");

            }

            return response.json();

        })

        .then(v => {

            const lista = document.getElementById("listaPrenotazioni");

            let badge = "bg-warning text-dark";

            if (v.statoVisita === "CONFERMATA")
                badge = "bg-success";

            if (v.statoVisita === "ANNULLATA")
                badge = "bg-danger";

            lista.innerHTML = `

<div class="card shadow-lg">

<div class="card-body">

<h3 class="text-primary mb-3">

🏛 ${v.museo.nomeMuseo}

</h3>

<hr>

<p><strong>Codice Prenotazione:</strong> ${v.codicePrenotazione}</p>

<p><strong>Visitatore:</strong> ${v.ospite.nome} ${v.ospite.cognome}</p>

<p><strong>Email:</strong> ${v.ospite.email}</p>

<p><strong>Data visita:</strong> ${v.dataVisita}</p>

<p><strong>Ora:</strong> ${v.oraVisita}</p>

<p><strong>Numero partecipanti:</strong> ${v.numOspiti}</p>

<p>

<strong>Stato:</strong>

<span class="badge ${badge}">

${v.statoVisita}

</span>

</p>

<div class="mt-4">

<button
id="btnRicevuta"
class="btn btn-primary me-2">

📄 Ristampa ricevuta

</button>

${v.statoVisita !== "ANNULLATA" ? `

<button
id="btnAnnulla"
class="btn btn-danger">

❌ Annulla prenotazione

</button>

` : `

<button
class="btn btn-secondary"
disabled>

Prenotazione annullata

</button>

`}

</div>

</div>

</div>

`;

            // ===========================
            // Ristampa ricevuta
            // ===========================

            document.getElementById("btnRicevuta").addEventListener("click", () => {

                sessionStorage.setItem(
                    "ultimaPrenotazione",
                    JSON.stringify(v)
                );

                window.location.href = "conferma.html";

            });

            // ===========================
            // Annulla prenotazione
            // ===========================

            const btnAnnulla = document.getElementById("btnAnnulla");

            if (btnAnnulla) {

                btnAnnulla.addEventListener("click", () => {

                    const conferma = confirm(
                        "Vuoi davvero annullare questa prenotazione?"
                    );

                    if (!conferma) {
                        return;
                    }

                    fetch(`/api/visite/${v.idVisita}/annulla`, {

                        method: "PUT"

                    })

                    .then(() => {

                        alert("Prenotazione annullata con successo.");

                        document.getElementById("btnCarica").click();

                    });

                });

            }

        })

        .catch(() => {

            document.getElementById("listaPrenotazioni").innerHTML = `

<div class="alert alert-warning">

Nessuna prenotazione trovata con questo codice.

</div>

`;

        });

});

console.log(sessionStorage.getItem("ultimaPrenotazione"));
const visita = JSON.parse(sessionStorage.getItem("ultimaPrenotazione"));

if (!visita) {
    window.location.href = "index.html";
}

// ==============================
// Codice prenotazione
// ==============================

const codicePrenotazione = visita.codicePrenotazione;

// ==============================
// Compilazione dati
// ==============================

document.getElementById("codicePrenotazione").textContent =
    codicePrenotazione;

document.getElementById("museo").textContent =
    visita.museo.nomeMuseo;

document.getElementById("ospite").textContent =
    visita.ospite.nome + " " + visita.ospite.cognome;

document.getElementById("email").textContent =
    visita.ospite.email;

document.getElementById("telefono").textContent =
    visita.ospite.telefono;

document.getElementById("data").textContent =
    visita.dataVisita;

document.getElementById("ora").textContent =
    visita.oraVisita;

document.getElementById("numOspiti").textContent =
    visita.numOspiti;



// ==============================
// Badge stato
// ==============================

let badge = "bg-warning text-dark";

if (visita.statoVisita === "CONFERMATA") {
    badge = "bg-success";
}

if (visita.statoVisita === "ANNULLATA") {
    badge = "bg-danger";
}

document.getElementById("stato").innerHTML =

`<span class="badge ${badge}">
    ${visita.statoVisita}
</span>`;

// ==============================
// Messaggio finale
// ==============================

document.getElementById("messaggioFinale").innerHTML = `

<strong>Importante</strong>

<br><br>

La prenotazione è stata registrata con successo.

<br><br>

Conserva il codice

<strong>${codicePrenotazione}</strong>

perché ti servirà per:

<ul class="mt-2 mb-0">

<li>consultare la prenotazione;</li>

<li>visualizzarne il riepilogo;</li>

<li>annullarla se necessario.</li>

</ul>

`;

// ==============================
// Pulsanti
// ==============================




async function generaPDF(salva = true) {

    const { jsPDF } = window.jspdf;

    const pdf = new jsPDF({

        orientation: "portrait",
        unit: "mm",
        format: "a4"

    });

pdf.setDrawColor(196,154,58);

pdf.setLineWidth(0.5);

pdf.roundedRect(10,10,190,277,4,4);

// ===== Header =====

pdf.setFillColor(16, 42, 67);
pdf.rect(0, 0, 210, 28, "F");

pdf.setTextColor(255, 255, 255);
pdf.setFont("helvetica", "bold");
pdf.setFontSize(20);

pdf.text("Musei Provinciali di Salerno", 105, 13, {
    align: "center"
});

pdf.setFontSize(10);

pdf.text(
    "Sistema di Prenotazione Online",
    105,
    21,
    { align: "center" }
);

// ===== Titolo =====

pdf.setTextColor(0,0,0);

pdf.setFontSize(18);

pdf.text(
    "Ricevuta di Prenotazione",
    105,
    40,
    { align:"center" }
);

pdf.setDrawColor(196,154,58);
pdf.setLineWidth(.6);
pdf.line(20,46,190,46);

    // ======= Dati =======

pdf.setFont("helvetica", "bold");
pdf.setFontSize(14);

pdf.text("Codice Prenotazione", 105, 60, {
    align: "center"
});

pdf.setFontSize(20);

pdf.setTextColor(16,42,67);

pdf.text(
    visita.codicePrenotazione,
    105,
    70,
    {
        align:"center"
    }
);

pdf.setTextColor(0,0,0);

pdf.setFontSize(12);

let y = 90;

function riga(titolo,valore){

    pdf.setFont("helvetica","bold");

    pdf.text(titolo,35,y);

    pdf.setFont("helvetica","normal");

    pdf.text(valore,80,y);

    y += 12;

}

riga("Museo", visita.museo.nomeMuseo);

riga("Visitatore",
    visita.ospite.nome + " " + visita.ospite.cognome);

riga("Email",
    visita.ospite.email);

riga("Telefono",
    visita.ospite.telefono);

riga("Data visita",
    visita.dataVisita);

riga("Ora",
    visita.oraVisita);

riga("Partecipanti",
    visita.numOspiti.toString());

riga("Stato",
    visita.statoVisita);

    // ==========================
// Codice a barre
// ==========================

const canvas = document.getElementById("barcodeCanvas");

JsBarcode(canvas, visita.codicePrenotazione, {

    format:"CODE128",

    width:2,

    height:55,

    displayValue:true,

    fontSize:16

});

const barcode = canvas.toDataURL("image/png");

pdf.addImage(

    barcode,

    "PNG",

    45,

    y + 8,

    120,

    25

);


pdf.setDrawColor(196,154,58);

pdf.line(20,245,190,245);

pdf.setFont("helvetica","italic");

pdf.setFontSize(11);

pdf.text(

    "Presentare questa ricevuta all'ingresso del museo.",

    105,

    255,

    {

        align:"center"

    }

);

pdf.setFont("helvetica","normal");

pdf.setFontSize(10);

pdf.text(

    "Grazie per aver scelto i Musei Provinciali di Salerno.",

    105,

    263,

    {

        align:"center"

    }

);

    if (salva) {

    pdf.save(`Ricevuta_${visita.codicePrenotazione}.pdf`);

} else {

    window.open(pdf.output("bloburl"));

}

}

async function scaricaPDF() {

    await generaPDF(true);

}

async function stampaPDF() {

    await generaPDF(false);

}

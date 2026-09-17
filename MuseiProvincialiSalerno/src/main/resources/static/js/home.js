fetch("/api/musei")
.then(response => response.json())
.then(musei => {

    const contenitore = document.getElementById("museiHome");


    musei.forEach(museo => {

        contenitore.innerHTML += `

        <div class="card-museo">

            <img src="img/${museo.foto}" 
            alt="${museo.nomeMuseo}">


            <div class="card-body-home">


                <h4>
                ${museo.nomeMuseo}
                </h4>


                <p>
                ${museo.descrizione.substring(0,120)}...
                </p>


                <a href="musei.html" 
                class="btn-scopri">

                    Scopri di più

                </a>


            </div>


        </div>

        `;

    });

});



const slider = document.getElementById("museiHome");


document.getElementById("nextMusei").onclick = () => {

    slider.scrollBy({
        left:380,
        behavior:"smooth"
    });

};


document.getElementById("prevMusei").onclick = () => {

    slider.scrollBy({
        left:-380,
        behavior:"smooth"
    });

};
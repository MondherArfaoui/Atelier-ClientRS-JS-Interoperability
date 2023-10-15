function getDetails(){
    var delegation = document.getElementById("delegation");
    var url = "http://localhost:8070/LogementRendezVous_Etudiant_war_exploded/api/logements?delegation=" + delegation.value;
    xmlhttp.open('GET', url, true);
    xmlhttp.send(null);

    xmlhttp.onreadystatechange = function() {
        var reference = document.getElementById("reference");
        var adresse = document.getElementById("adresse");
        var gouvernorat = document.getElementById("gouvernorat");
        if (xmlhttp.readyState == 4) {
            if (xmlhttp.status == 200) {
                var resultat = JSON.parse(xmlhttp.responseText);
                reference.value = "";
                adresse.value = "";
                gouvernorat.value = "";
                for (var i in resultat) {
                    reference.value = reference.value +" "+ resultat[i].reference;
                    adresse.value = adresse.value +" "+ resultat[i].adresse;
                    gouvernorat.value = gouvernorat.value +" "+ resultat[i].gouvernorat;
                }
            } else {
                alert("Erreur -> " + xmlhttp.responseText);
            }
        }
    }
}
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
	<head>
		<title> Accueil - Recherche site </title>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <link href="resources/css/index.css" rel="stylesheet"> 
	</head> 
	<body>
	<form method ="post" action="result">
		<input type = "text" name="preferences" placeholder="Ex: Musée, Quartier..">
		<input type = "date" name= "dateJour" min ="2017-01-21">
		<input type = "time" name= "heureArrivee">
		<input type = "time" name= "heureDepart" date-min="heureArrivee">
		<input type = "submit">
	</form>

	</body>
</html>

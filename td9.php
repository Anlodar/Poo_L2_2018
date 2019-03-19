<?php
	include("include/fonctions.inc.php");
	include ("include/util.inc.php");
	include ("traitement.php");
	include_once ("./include/20_fonctions_calendar.inc.php");
	const N = 10;
?> 

<!DOCTYPE html>
<html lang="fr">
<head>
	<title>Contenu du TD 9 en php</title>
	<meta charset="utf-8" />
	<link rel="stylesheet" type="text/css" href="../css/styles.css">
	<link rel="stylesheet" type="text/css" href="../css/styles2.css">
    <style> 
    	.texthf
		{
		    color: white;
		    font-family: Arial;
		    font-size: 14pt;
		    margin-bottom: 0.5cm;
		    margin-top: 0.5cm;
		    text-align:center; 
		}
   </style>
</head>

<body>
	<header>
		<a id="haut"></a>
		<p>
	   	 	<img src="../images/img5.jpg" alt="Logo de l'université de Cergy Pontoise" style="float:left;	width: 200px; height: 80px; margin-left: 20px; margin-bottom: 10px; margin-top: 5px"/>
	  		<img src="../images/img6.jpg" alt="Logo du Réseau Figure" style="float:right;	width: 200px;
	height: 60px; margin-right: 20px; margin-bottom: 10px; margin-top: 10px" />
   		 </p>
   		 <h1 class="texthf">TD9 : formulaires HTML et PHP</h1>
	</header>

	 <nav id="navigation">
		 	 <ul>
		 	 	<li><a href="./td5.php">TD 5</a></li>
		 	 	<li><a href="./td6.php">TD 6</a></li>
		 	 	<li><a href="./td7.php">TD 7</a></li>
		 	 	<li><a href="./td8.php">TD 8</a></li>
		 	 	<li><a href="./td9.php">TD 9</a></li>
		 	 	<li><a href="./td10.php">TD 10</a></li>
				<li><a href="../html/pageconstruction.html">Projet n°1</a></li>
				<li><a href="../html/pageconstruction.html">Projet n°2</a></li>
				<li><a href="../html/pageconstruction.html">Projet n°3</a></li>
			</ul> 
	</nav> 

	<aside>
		<p>
			Pour accèder aux pages suivantes veuillez cliquer sur les liens suivants :
		</p>
		<p>
			<a href="../index.html">Accueil</a>
		</p>
		<ol>
			<li><a href="./td5.php">TD 5</a></li>
			<li><a href="./td6.php">TD 6</a></li>
			<li><a href="./td7.php">TD 7</a></li>
			<li><a href="./td8.php">TD 8</a></li>
			<li><a href="./td9.php">TD 9</a></li>
			<li><a href="./td10.php">TD 10</a></li>
			<li><a href="../html/pageconstruction.html">Projet n°1</a></li>
			<li><a href="../html/pageconstruction.html">Projet n°2</a></li>
			<li><a href="../html/pageconstruction.html">Projet n°3</a></li>
		</ol>
	</aside>

	<section class="section">
	<h2>Exercice 1 : test de la méthode get </h2>
	<form  method="get" action="#" >
		<fieldset>
			<legend>Rentrez votre nom</legend>
			<label> Nom </label>
			<input type="text" name="nom" />
			<input type="submit" value="Valider">
		</fieldset>
	</form>
	<?php
		echo methodeGet();
	?>
	</section>

	<section class="section">
	<h2>Exercice 2 : test des méthodes get et post</h2>
	<form  method="get" action="#" >
		<fieldset>
			<legend>Rentrez les valeurs souhaitées</legend>
			<label> Nom </label>
			<input type="text" name="nom2" />
			<label> Décimal </label>
			<input type="text" name="dec" />
			<input type="submit" value="Valider">
		</fieldset>
	</form>
	<?php
		echo methodeGet2();
	?>
	</section>

	<section class="section">
	<h2>Exercice 3 : formulaire de saisie d'URL </h2>
	<form  method="get" action="#" >
		<fieldset>
			<legend>Rentrez un URl</legend>
			<label> URL </label>
			<input type="text" name="url" />
			<input type="submit" value="Valider">
		</fieldset>
	</form>
	<?php
		echo methodeURL();
	?>
	</section>

	<section class="section">
	<h2>Exercice 4 : formulaire avec liste d'options </h2>
	<form  method="post" action="#" >
		<fieldset>
			<legend>Choississez ce que vous souhaiter</legend>
			<select name="tblMulti">
				<option value=""> ----- Choisir ----- </option>
				<?php
					echo listeOptions();
				?>
			</select>
			<input type="submit" value="Valider">
		</fieldset>
	</form>
	<?php
		echo formuMultiplication();
	?>
	</section>

	<section class="section">
	<h2>Exercice 5 : formulaire avec cases à cocher </h2>
	<form  method="post" action="#" >
		<fieldset>
			<legend> Cochez les cases que vous souhaitez </legend>
			<table>
			<tr>
				<td> User </td>
				<td> <input type="checkbox" name="choix[]" value="4"> r </td>
		    	<td> <input type="checkbox" name="choix[]" value="2"> w </td>
		    	<td> <input type="checkbox" name="choix[]" value="1"> x </td>
		    </tr>
		    <tr>
		    	<td> Group </td>
		    	<td> <input type="checkbox" name="choix2[]" value="4"> r </td>
		    	<td> <input type="checkbox" name="choix2[]" value="2"> w </td>
		    	<td> <input type="checkbox" name="choix2[]" value="1"> x </td>
		    </tr>
		    <tr>
		    	<td> Others </td>
		    	<td> <input type="checkbox" name="choix3[]" value="4"> r </td>
		    	<td> <input type="checkbox" name="choix3[]" value="2"> w </td>
		    	<td> <input type="checkbox" name="choix3[]" value="1"> x </td>
		    </tr>
			</table>
			<input type="submit" value="Valider" name="en">
		</fieldset>
    </form>
	<?php
		echo cocherOptions('choix');
		echo cocherOptions('choix2');
		echo cocherOptions('choix3');
	?>
	</section>

	<section class="section">
	<h2>Exercice 6 :  formulaire généré par des boucles PHP avec liste d'options  </h2>
	<form  method="post" action="#" >
		<fieldset>
			<legend> Veuillez choisir les options souhaitées  </legend>
			<select name="oMois">
			<option value=""> ----- Choisir le mois ----- </option>
			<?php
				echo listeOptionsMois();
			?>
			</select>
			
			<select name="oAnnee">
			<option value=""> ----- Choisir l'année ----- </option>
			<?php
				echo listeOptionsAnnee();
			?>
			</select>
			<input type="submit" value="Valider">
		</fieldset>
	</form>
	<?php
		echo formuDate();
	?>
	</section>

	<section class="section">
	<h2>Exercice 7 : formulaire avec bouton radio </h2>
	<form name="form1" method="post" action="#">
		<fieldset>
			<legend> Remplissez et cochez les cases </legend>
			<input type="text" name="octale" />
			<label> Valeur octale </label>
			<input type="radio" name="fichier" value="1">
			<label> Fichier </label>
			<input type="radio" name="dossier" value="2" >
			<label> Répertoire </label>
			<input type="submit" value="Envoyer">
		</fieldset>
	</form>
	<?php
		echo formuRadio();
	?>

	</section>

	<footer>
	    <h2 class="texthf">Solène Bisch, L2 CMI SIC, année 2018</h2>
    	<?php 
    		echo '<p class="texthf">' . dateCreate('fr'). '</p>';
    		echo '<p class="texthf">' . $_SERVER['HTTP_USER_AGENT'] . '</p>';
    	?>
	    <p>
	    	<a class="liennav" href="#haut">Aller en haut de la page</a>
		</p>
	</footer>

</body>

</html> 
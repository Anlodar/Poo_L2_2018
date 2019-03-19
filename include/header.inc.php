<!DOCTYPE html>
<html lang="fr">
<head>
	<title>Contenu du TD 8 en php</title>
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
   		 <?php
   			if(isset($_GET['style'])) if($_GET['style']=="alternatif") echo '<link rel="stylesheet" href="../css/styles_alternatifs.css" />';
   		?>
   		<h1 class="texthf">TD8 : PHP - tableaux et fonctions</h1>
	</header>

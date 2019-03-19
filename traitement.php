<?php
	//Fonction qui retourne en majuscule le mot donné
	function methodeGet() {
		if(isset($_GET['nom']))
			return strtoupper($_GET['nom']);
	}

	//Fonction qui retourne en majuscule le mot donné et qui retourne en héxadécimal le nombre décimal
	function methodeGet2() {
		$resultat='';
		if(isset($_GET['nom2']) && isset($_GET['dec']))
			return strtoupper($_GET['nom2']) . ' et ' . dechex($_GET['dec']);
		
	}

	//Fonction qui retourne la fonctin donneURL
	function methodeURL() {
		if (isset($_GET['url'])) {
			return donneURL($_GET['url']);
		}
	}

	//Fonction qui créer la liste du formulaire
	function listeOptions(){
		$result = "";
		for($i=2;$i<=16;$i++){
			$result .= "<option value='$i'>" . $i . '</option>' ;
		}
		return $result;
	}

	//Fonction qui retourne la table de multiplication suite au choix du nombre dans la liste du formulaire
	function formuMultiplication() {
		if (isset($_POST['tblMulti'])) {
			return multiplication2($_POST['tblMulti']);
		}
	}

	//Fonction associée aux cases à cocher, elle retourne la valeur octale de la commande "chmod"
	function cocherOptions($value){
		$tabChoix = (isset($_POST[$value]))?$_POST[$value]:null;
		$resultat = "";
		if (empty($tabChoix)) {
			$resultat = 0;
		}
	  	else {
	  		foreach($tabChoix as $cle => $valeur) {
				$resultat += $valeur;
	  		}
	  	}
  		return $resultat;
	}

	//Création de la liste déroulante des mois 
	function listeOptionsMois(){
		$result = "";
		for($i=1;$i<=12;$i++){
			$result .= "<option value='$i'>" . $i . '</option>' ;
		}
		return $result;
	}

	//Créer la liste des années pour le formulaire
	function listeOptionsAnnee(){
		$result = "";
		for($i=2000;$i<=2030;$i++){
			$result .= "<option value='$i'>" . $i . '</option>' ;
		}
		return $result;
	}

	//Retourne le mois et/iou l'année selon les choix dans le formulaire
	function formuDate(){
		if (isset($_POST['oMois']) && isset($_POST['oAnnee'])) {
			if($_POST['oMois']=="")
				return show_calendar($_POST['oAnnee']);
			else 
				return table_month($_POST['oMois'],$_POST['oAnnee']);
		}
	}

	//Retourne le type de fichier et le résultat de la valeur octale de "chmod" indiquée dans le formulaire
	function formuRadio() {
		if(isset($_POST['octale']) && isset($_POST['fichier']))
			return '-f ' . permission($_POST['octale']);
		if(isset($_POST['octale']) && isset($_POST['dossier']))
			return '-d ' . permission($_POST['octale']);
		else
			return 0;
	}
?>
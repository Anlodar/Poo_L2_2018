<?php
session_start();
	//Compteur de visite du site avec sauvegarde dans le fichier texte
	function compteur_hits(){
		// sauvegarde la variablie $hit dans le fichier de session
		$hits =0;

		if(empty($hits)){
			$compteur_f = fopen("../../compteur_visites.txt","a+"); 
			$num = fgets($compteur_f,4096);
			fclose($compteur_f); 
			$hits = $num + 1; 
			$compteur_f = fopen("../../compteur_visites.txt","w");
			fputs($compteur_f,$hits);
			fclose($compteur_f);
		}
		else echo "no";
		return $hits;
	}

	//Sélectionne aléatoirement une image
	function ImgAleatoire(){
		$resultat = "";
		$all_photos = glob('photos/*.*');
		$noms_images = $all_photos[array_rand($all_photos)];
		$format_supporte = array('gif','jpg','jpeg','png');
		$ext = strtolower(pathinfo($noms_images, PATHINFO_EXTENSION));
		if (in_array($ext, $format_supporte)) {
			$resultat .= '<figure>' . '<img src="'.$noms_images .'" alt= "'.$noms_images.'" />' . '<figcaption>' . "Random Image : ". $noms_images." . '</figcaption>' " .'</figure>';
		}
		return $resultat;
	} 


	// La fonction dateCreate retourne la date du jour, le mois et l'année. Cette date est donnée en français(fr paramètre donner par défaut) ou en anglais (n'importe quel autre mot passer en paramètre)
 	function dateCreate($langue){
 		$joursFr = array('Lundi','Mardi','Mercredi','Jeudi','Vendredi','Samedi','Dimanche');
		$joursEn = array('Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday');
		$moisFr = array('Janvier','Février','Mars','Avril','Mai','Juin','Juillet','Aout','Septembre','Octobre','Novembre','Décembre');
		$moisEn = array('January','February','March','April','May','June','July','August','September','October','November','December');

		$jour = date('N');
		$mois = date('m');
		$annee = date('Y');

		if ($langue == 'fr'){
			return $joursFr[$jour-1] . ' ' . $jour . ' ' . $moisFr[$mois-1] . ' ' . $annee;
		}
		else{
			return $joursEn[$jour-1] . ', ' . $moisEn[$mois-1] . ' ' . $jour . ', ' .  ' ' . $annee;
		}

 	}

 	//donneURL donne les composants (protocole, TLD, nom de la machine) du lien internet passer en argument
 	function donneURL($lienURL){
 		return ('Le protocole est le suivant: ' . explode("://",$lienURL)[0] . '<br/>' . 'Le Top Level Domain est le suivant : ' . explode(".",$lienURL)[2] . '<br/>' . 'L organisme est le suivant : ' . explode(".",$lienURL)[1] . '<br/>' . 'Le nom de la machine : ' . explode(".",explode("://",$lienURL)[1])[0] . '<br/>');
 	}

 	/*
 	function calendrierMois() {
 		$joursFr = array('Lun','Ma','Me','Je','Ve','Sa','Di');
		$moisFr= array('Janvier','Février','Mars','Avril','Mai','Juin','Juillet','Aout','Septembre','Octobre','Novembre','Décembre');
		$jour_par_mois = array('31','28','31','30','31','30','31','31','30','31','30','31');

		$jour = date('N');
		$mois = date('m');
		$annee = date('Y');
		$calendrier = "";

	
			$calendrier.= '<table>' . '<tr>' . '<th>' . $moisFr[$mois-1] . '</th>' . '</tr>' . '<tr>' ;
			for($i=0; $i<8; $i++){
				$calendrier .= '<th>' . $joursFr[$i] . '</th>' ;
			}
			$calendrier .= '</tr>';
			return $calendrier;
		
 	}

	*/
?>
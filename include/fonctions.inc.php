<?php
	//Affiche la table ASCII limitée aux 128 premiers caractères (de 0 à 127), sauf les 32 premiers (de 0 à 31)). 
	function tableAscii(){
		$resultat = "";
		$resultat .= '<table class="tab6">';
		$resultat .= '<caption>' . 'Illustration 1 : table ASCII' . '</caption>';
		$resultat .= '<tr>';
		for($i=-1; $i<16;$i++){
				if ($i==-1) {
				 	$resultat .=('<th>' . ' ' . '</th>');						
				}
				else{
					$resultat .=('<th>' . dechex($i) . '</th>');
				}
		}
		$resultat .= '</tr>';

		$ascii=32;
		for($i=2; $i<=7;$i++){
			$resultat .=('<tr>' . '<th>' . $i . '</th>');
			for($j=0; $j<16;$j++){
				if (($ascii>=48)&&($ascii<=57)) {
					$resultat .=( '<td class="rouge">'. chr($ascii). '</td>');
				}
				else if (($ascii>=65)&&($ascii<=90)) {
					$resultat .=( '<td class="vert">'. chr($ascii). '</td>');
				}
				else if (($ascii>=97)&&($ascii<=122)) {
					$resultat .=( '<td class="bleu">'. chr($ascii). '</td>');
				}
				else{
					if($ascii==60){
						$resultat .=( '<td>' . '&lt;' . '</td>');
					}
					else if($ascii==62){
						$resultat .=( '<td>' . '&gt;' . '</td>');
					}
					else if($ascii==127){
						$resultat .=( '<td>' . 'Del' . '</td>');
					}
					else{
						$resultat .=( '<td>'. chr($ascii). '</td>');
					}
				}
				$ascii++;
			}
			$resultat .= '</tr>';
		}		
		$resultat .= '</table>';
		return $resultat;
	}

	//Afficher la palette web "sécurisée" (Safe Web Palette) de 216 couleurs.
	function tableColor(){
		$resultat = "";
		$resultat .= "<table>";
		for ($r = 0; $r < 256; $r += 51){
		 	$R = str_pad (dechex ($r), 2, "00", STR_PAD_LEFT);
		 	for ($g = 0; $g < 256; $g += 51){
		 		$G = str_pad (dechex ($g), 2, "00", STR_PAD_LEFT);
		 		$resultat .= "<tr>";
		   		for ($b = 0; $b < 256; $b += 51){
		   			$couleur = strtoupper ($R . $G . str_pad (dechex ($b), 2, "00", STR_PAD_LEFT));
		    		$resultat .= "<td style='background-color:#$couleur'>";
		    		$resultat .= "#" . $couleur;
		    		$resultat .= "</td>";
		   		}
		   		$resultat .= "</tr>";
		  	}
		}
		$resultat .= "</table>";
		return $resultat ;
	}

	// La fonction Hello retourne "Hello" selon la valeur du paramètre passer en argument
	function Hello($nb_fois) {
		$i = 1;
		$resultat = "";
		while( $i != $nb_fois+1){
			$resultat .= 'Hello numéro '. $i . '<br/>';
			$i = $i+1;
		}
		return $resultat;
	}

	//Affiche, de deux façons différentes, les chiffres hexadécimaux de 0 a F
	function fonctionPred(){
		$resultat = "";
		$resultat .= '<p>' ;
		for($i=0;$i<=15;++$i){
			$resultat .=  (dechex($i) . ' - ');	//Affichage avec la fonction dechex()
		}

		$resultat .=  '<br/>' ;
		for($i=0;$i<=15;++$i){
			printf(" %x - ",$i);	//Affichage avec printf
		}
		$resultat .= '</p>';
		return $resultat;
	}

	//Affiche la table de multiplication (10 x 10)
	function multiplication(){
		$resultat = "";
		$resultat .= '<table class="tabphp">';
		$resultat  .= '<caption>' . 'Illustration 1 : table de multiplication' . '</caption>';
		$resultat .= '<tr>';
		for($i=0; $i<=10;$i++){
			$resultat .= '<th>' . $i . '</th>';
		}
		$resultat .=  '</tr>';

		for($i=1; $i<=10;$i++){
			$resultat .= ('<tr>' . '<th>' . $i . '</th>');
			for($j=1; $j<=10;++$j){
				$resultat .= ( '<td>'.$i*$j. '</td>');
			}
			$resultat .=  '</tr>';
		}
		$resultat .=  '</table>';
		return $resultat;
	}

	//Affiche de 2 manières différentes les 2 conversions suivantes : • 0x41 = 65 = 'A' • 0x2B = 43 = '+'
	function conversionAscii(){
		$resultat = "";
	 	$resultat .=  '<p>' . dechex(ord("A")) . ' = ' . ord("A") . ' = '. "'" . chr(ord("A")). "'" . '<br/>' . '</p>' ;
	 	$resultat .=  '<p>' . '0x41' . ' = ' . hexdec('0x41') . ' = ' . "'" . chr(0x41). "'" . '</p>';
	 	
	 	$resultat .=  '<p>' . dechex(ord("+")) . ' = ' . ord("+") . ' = ' . "'" . chr(ord("+")) . "'" . '<br/>' . '</p>';
	 	$resultat .=  '<p>' . '0x2B' . ' = ' . hexdec('0x2B') . ' = ' . "'" . chr(0x2B) . "'" . '<br/>' . '</p>';
	 	return $resultat;
	}

	//Affiche tous les nombres de 0 à 17 en bases 2, 8, 10, 16 (binaire, octal, décimal, hexadécimal)
	function conversion(){
		$resultat = "";
		$resultat .= '<table class="tabphp2">';
		$resultat .= '<caption>' . 'Illustration 2 : conversions bases 2, 8, 10, 16.' . '</caption>';
		$resultat .= '<tr>';
				$resultat .='<th class="tblco">' . 'binaire' . '</th>';
				$resultat .='<th>' . 'octal' . '</th>';
				$resultat .='<th>' . 'décimal' . '</th>';
				$resultat .='<th>' . 'héxadécimal' . '</th>';
		$resultat .= '</tr>';

		for($i=0; $i<=17;++$i){
			$resultat .=('<tr>' . '<td>' . decbin($i) . '</td>' . '<td>' . decoct($i) . '</td>' . '<td>' . $i . '</td>' . '<td>' . dechex($i) . '</td>' . '</tr>');	
		}
		$resultat .= '</table>';
		return $resultat;
		}

	//Affiche la table de multiplication selon le paramètre passer en argument ( paramètre x paramètre)
	function multiplication2($value){
		$resultat = "";
		$resultat .= '<table class="tabphp">';
		$resultat .= '<caption>' . 'Illustration 1 : table de multiplication' . '</caption>';
		$resultat .= '<tr>';
		for($i=0; $i<= $value ;$i++){
			$resultat .=('<th>' . $i . '</th>');
		}
		$resultat .= '</tr>';

		for($i=1; $i<= $value ;$i++){
			$resultat .=('<tr>' . '<th>' . $i . '</th>');
			for($j=1; $j<= $value;$j++){
				$resultat .=( '<td>'.$i*$j. '</td>');
			}
			$resultat .= '</tr>';
		}
		$resultat .= '</table>';
		return $resultat;
	}

//Affiche tous les nombres de 0 à $value2(paramètre passer en argument) en bases 2, 8, 10, 16 (binaire, octal, décimal, hexadécimal)
	function conversion2($value2, $option){
		$resultat = "";
		$resultat .= '<table class="tabphp2">';
		$resultat .= '<caption>' . 'Illustration 1 : conversions bases 2, 8, 10, 16.' . '</caption>';
		if ($option=='ligne') {
			$resultat .= '<tr>';
					$resultat .='<th class="tblco">' . 'binaire' . '</th>';
					$resultat .='<th>' . 'octal' . '</th>';
					$resultat .='<th>' . 'décimal' . '</th>';
					$resultat .='<th>' . 'héxadécimal' . '</th>';
			$resultat .= '</tr>';

			for($i=0; $i<=N;++$i){
				$resultat .=('<tr>' . '<td>' . decbin($i) . '</td>' . '<td>' . decoct($i) . '</td>' . '<td>' . $i . '</td>' . '<td>' . dechex($i) . '</td>' . '</tr>');	
			}
		}
		else {
			$resultat .= '<tr>' . '<th class="tblco">' . 'binaire' . '</th>';
			for ($i=0; $i <= N; $i++) { 
				$resultat .=( '<td>' . decbin($i) . '</td>' );	
			}
			$resultat .= '<tr>' . '<th>'. 'octal' . '</th>';
			for ($i=0; $i <= N; $i++) { 
				$resultat .=( '<td>' . decoct($i) . '</td>' );	
			}
			$resultat .= '<tr>' . '<th>'. 'decimal' . '</th>';
			for ($i=0; $i <= N; $i++) { 
				$resultat .=( '<td>' . $i . '</td>' );	
			}
			$resultat .= '<tr>' . '<th>'. 'héxadécimal' . '</th>';
			for ($i=0; $i <= N; $i++) { 
				$resultat .=( '<td>' . dechex($i) . '</td>' );	
			}
			$resultat .= '</tr>';

		}
		$resultat .= '</table>';
		return $resultat ;
	}

	//Affiche une page HTML contenant les exercices 1 à 4 du TD 5 (à la manière de phpinfo() )
	function pageHTML(){

		$resultat = "";

		$resultat .= "<table class = tabtd8 >"."<tr class=exo>"."<td class=desc>"."Exercice 1"."</td>"."<td class=e>".Hello(20)."</td>"."</tr>"
		."<tr class= exo>"."<td class=desc>"."Exercice 2"."</td>"."<td class=e>".date("F j, Y, g:i a")."</td>"."</tr>"
		."<tr class=exo>"."<td class=desc>"."Exercice 3"."</td>"."<td class=e>".fonctionPred()."</td>"."</tr>"
		."<tr class=exo>"."<td class=desc>"."Exercice 4"."</td>"."<td class=e>".multiplication()."</td>"."</tr>"."</table>";

		return $resultat;
	}

	//Affiche les droits des utilisateurs
	function permission($valOctale){
		$arr = str_split($valOctale);
		$result = "";
		for($index=0;$index<3;$index++){
			switch ($arr[$index]) {
				case '0':
				$result .= "--- ";
				break;
				case '1':
				$result .= "--x ";
				break;
				case '2':
				$result .= "-w- ";
				break;
				case '3':
				$result .= "-wx ";
				break;
				case '4':
				$result .= "r-- ";
				break;
				case '5':
				$result .= "r-x ";
				break;
				case '6':
				$result .= "rw- ";
				break;
				case '7':
				$result .= "rwx ";
				break;
				default:
				$result .= "ERR ";
				break;
			}
		}
		return $result;
	}
?>
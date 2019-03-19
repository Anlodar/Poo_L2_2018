 <?php

define("TINY", 2);
define("MEDIUM", 3);
define("LARGE", 4);
define("HUGE", 6);
define("DEFAULT_YEAR", 2010);

$jours = array(1=>"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche");
$mois = array(1=>"Janvier", "F&eacute;vrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Ao&ucirc;t", "Septembre", "Octobre", "Novembre", "D&eacute;cembre");

/**
 * generation d'un mois du calendrier sous la forme d'un petit tableau HTML 
 * @param $mois (int) : le numero du mois 
 * @param $year (int) : l'annee sur 4 chiffres
 * @return le tableau HTML du mois genere
 */
function table_month($month, $year) {
    if ($month < 1 || $month > 12) { 
        $month = 1; 
    }
    if ($year < 1900 || $year > 2100) {
        $year = DEFAULT_YEAR; 
    }
    global $jours, $mois;
    
    $timestamp = mktime(0, 0, 0, $month, 1, $year);
    $nb_jours = date("t", $timestamp);
    $no_jour = 0;
    if (date("n") == $month && date("Y") == $year) {
        $current_day = date("j");
    }
    else {
        $current_day = 0;
   }
   
   $table = "<table style='font-size:8pt; width:50px;'><caption>".$mois[date("n", $timestamp)]."</caption>";
    $table .= "<tr>";    // ligne de titre
    for ($i = 1 ; $i <=7 ; $i++) {
        $table .= "<th style='font-family:sans-serif; background-color:#09F;'>".substr($jours[$i], 0, 2)."</th>";
    }
    $table .= "</tr>\n";
    
    $table .= "<tr>";    // cas particulier du début du mois : i.e. de la premiere semaine
    for($i = 1 ; $i < date("N", $timestamp) ; $i++) {
        $table .= "<td style=\"border:0px;\">&nbsp;</td>";
    }
    for($jour = $i ; $jour <= 7 ; $jour++) {
        $no_jour++;
        $table .= "<td".($current_day == $no_jour?" style='background-color:red;'":($jour == 6 || $jour == 7?" style='background-color:#ccc;'":"")).">".$no_jour."</td>";
    }    
    $table .= "</tr>\n";

    while($no_jour + 7 < $nb_jours) {    // cas des semaines pleines
        $table .= "<tr>";
        for ($jour = 1 ; $jour <= 7; $jour++) {
            $no_jour++;
            $table .= "<td".($current_day == $no_jour?" style='background-color:red;'":($jour == 6 || $jour == 7?" style='background-color:#ccc;'":"")).">".$no_jour."</td>";
        }
        $table .= "</tr>\n";            
    }

    if ($no_jour < $nb_jours) { // dernière semaine du mois si incomplète
        $table .= "<tr>";
        $jour = 1;
        while($no_jour < $nb_jours) {
            $no_jour++;
            $table .= "<td".($current_day == $no_jour?" style='background-color:red;'":($jour == 6 || $jour == 7?" style='background-color:#ccc;'":"")).">".$no_jour."</td>";
            $jour++;
        }
        for($i = $jour ; $i <= 7 ; $i++) { // cas particulier de la fin du mois
            $table .= "<td style=\"border:0px;\">&nbsp;</td>";
        }    
        $table .= "</tr>\n";    
    }
    $table .= "</table>\n";
   return $table;
}

/**
 * affiche par defaut 3 mois avec le mois courant au milieu
 * sinon affiche l'ensemble du calendrier d'une annee
 * @param $year (int) : l'annee sur 4 chiffres
 * @param $style (int) : le style d'affichage (TINY / MEDIUM / LARGE / HUGE)
 */
function show_calendar($year = DEFAULT_YEAR, $style = LARGE) {
    if(func_num_args() == 0) { // on n'affiche que 3 mois (avec le mois courant au milieu) 
        $current_year = date("Y");
        $current_month = date("n");
        
        switch($current_month) {
            case 1 : // janvier
                $previous_month = 12;
                $previous_year = $current_year - 1;
                $next_month = $current_month + 1;
                $next_year = $current_year;
                break;
            case 12 : // decembre
                $previous_month = $current_month - 1;
                $previous_year = $current_year;
                $next_month = 1;
                $next_year = $current_year + 1;
                break;
            default:
                $previous_month = $current_month - 1;
                $previous_year = $current_year;
                $next_month = $current_month + 1;
                $next_year = $current_year;                
        }
        $calendar = "<table><tr>";
        $calendar .= "<td style='vertical-align:top;'>";
        $calendar .= table_month($previous_month, $previous_year);
        $calendar .= "</td>";
        $calendar .= "<td style='vertical-align:top;'>";
        $calendar .= table_month($current_month, $current_year);
        $calendar .= "</td>";
        $calendar .= "<td style='vertical-align:top;'>";
        $calendar .= table_month($next_month, $next_year);
        $calendar .= "</td>";            
        $calendar .= "</tr>\n</table>\n";            
    }
    else {
        if ($year < 1900 || $year > 2100) { $year = DEFAULT_YEAR; }
        $calendar = "<table><caption>".$year."</caption>";
        for($ligne = 0 ; $ligne < 12 / $style ; $ligne++) {
            $calendar .= "<tr>";
            for ($colonne = 1 ; $colonne <= $style ; $colonne++) {
                $calendar .= "<td style='vertical-align:top;'>";
                $calendar .= table_month($ligne * $style + $colonne, $year);
                $calendar .= "</td>";
            }
            $calendar .= "</tr>\n";
        }
        $calendar .= "</table>\n";
    }
    return $calendar;
}

?> 
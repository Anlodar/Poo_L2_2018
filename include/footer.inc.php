	<footer>
	    <h2 class="texthf">Solène Bisch, L2 CMI SIC, année 2018</h2>
    	<?php 
    		if(isset($_GET['lang'])){
					if($_GET['lang']=="fr") echo '<p class="texthf">'.dateCreate("fr").'</p>';
					else echo '<p class="texthf">'.dateCreate("en").'</p>';
				}
			else echo '<p class="texthf">'.dateCreate("en").'</p>';
    		echo '<p class="texthf">' . $_SERVER['HTTP_USER_AGENT'] . '</p>';
    	?>
	    <p>
	    	<a class="liennav" href="#haut">Aller en haut de la page</a>
		</p>
	</footer>

</body>

</html> 
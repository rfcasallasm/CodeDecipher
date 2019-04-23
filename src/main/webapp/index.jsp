<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
	<title>CodeDecipher</title>
	<link href="https://fonts.googleapis.com/css?family=VT323" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/normalize.css">
	<link rel="stylesheet" type="text/css" href="css/codedecipher.css">
	<script type="text/javascript" src="js/MooTools-Core.js"></script>
	<script type="text/javascript" src="js/three/three.js"></script>
	<script type="text/javascript" src="js/codedecipher.js"></script>
</head>
<body onload="onLoad()">
	<header>
		<h1>CodeDecipher</h1>
	</header>	
	<div id="viewer"></div>
	<footer>
		<form id="create-project-frm" method="get" action="CreateProject">
			<label for="url">URL GitHub</label>
			<input type="text" id="url" name="url" size="100" maxlength="500" >
			<button id="create-project-frm-submit" type="submit" >Crear</button>
		</form>
	</footer>
</body>
</html>

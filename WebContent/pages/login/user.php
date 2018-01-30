<?php
	$user=json_decode(file_get_contents("php://input"));
	if($user->username == '9' && $user->password == "admin"){
		session_start();
		$_SESSION['uid'] = uniqid('zxqk_');

		echo json_encode($_SESSION['uid']);
	}
	else{
		echo "unauthorized";
	}

?>
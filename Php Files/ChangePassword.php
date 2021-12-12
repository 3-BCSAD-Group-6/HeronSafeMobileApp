<?php 

require_once('DbOperation.php');
$response = array();


if($_SERVER['REQUEST_METHOD'] == 'POST'){
	if(isset($_POST['email']) and isset($_POST['password']) and isset($_POST['oldpassword']) and isset($_POST['updated_at'])){
		$db = new DbOperation();
		$result = $db->updatePassword($_POST['email'],$_POST['password'],$_POST['oldpassword'],$_POST['updated_at']);

		if($result == 1){

			$response['error'] = false;
			$response['message'] = "Password Updated Succesfully";

		}elseif($result == 2) {
			$response['error'] = true;
			$response['message'] = "Some error occured please try again";
		}
	}else {
		$response['error'] = true;
		$response['message'] = "Required field are missing";
	}
}
else {
	$response['error'] = true;
	$response['message'] = "Invalid Request";
}

echo json_encode($response);
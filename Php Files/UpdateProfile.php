<?php 

require_once('DbOperation.php');
$response = array();


if($_SERVER['REQUEST_METHOD'] == 'POST'){
	if(isset($_POST['email']) and isset($_POST['department']) and isset($_POST['contact_number']) and isset($_POST['gender']) and isset($_POST['updated_at'])){
		$db = new DbOperation();
		$result = $db->updateProfile($_POST['email'],$_POST['department'],$_POST['contact_number'],$_POST['gender'],$_POST['updated_at']);

		if($result == 1){

			$response['error'] = false;
			$response['message'] = "User Profile Updated Succesfully";

		}elseif($result == 2) {
			$response['error'] = true;
			$response['message'] = "Incorrect Old Password";
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
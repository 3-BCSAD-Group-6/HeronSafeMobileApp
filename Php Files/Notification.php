<?php 
$con = mysqli_connect('localhost', 'root', '', 'db_heronsafe');
$notification = array();
if(isset($_POST['email'])){
	$getEmail = $_POST['email'];
	$stmt = $con->prepare("SELECT email ,name, message, created_by, created_at FROM user_notifications WHERE email = ?");
	$stmt->bind_param("s", $getEmail);
	$stmt->execute();
	$stmt -> bind_result($email, $name, $message, $created_by, $created_at);

	while($stmt ->fetch()){

		$temp = array();
		$temp['email'] = $email;
		$temp['name'] = $name;
		$temp['message'] = $message;
		$temp['created_by'] = $created_by;
		$temp['created_at'] = $created_at;
	
		array_push($notification,$temp);
		}
}
	echo json_encode($notification);

?>
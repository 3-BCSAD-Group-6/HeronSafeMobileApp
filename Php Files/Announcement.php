<?php 
$con = mysqli_connect('localhost', 'root', '', 'db_heronsafe');

$stmt = $con->prepare("SELECT subject ,message, created_by, created_at FROM announcements order by created_at DESC limit 1");
$stmt->execute();
$stmt -> bind_result($subject, $message, $created_by, $created_at);

$announcement = array();

while($stmt ->fetch()){

    $temp = array();
    $temp['subject'] = $subject;
	$temp['message'] = $message;
	$temp['created_by'] = $created_by;
	$temp['created_at'] = $created_at;

	array_push($announcement,$temp);
	}

	echo json_encode($announcement);

?>
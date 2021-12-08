<?php 
$con = mysqli_connect('localhost', 'root', '', 'db_heronsafe');

$stmt = $con->prepare("SELECT student_id ,symptom, exposure, submitted_at, result, record_number FROM screenings");
$stmt->execute();
$stmt -> bind_result($symptom, $exposure, $submitted_at, $result, $record_number);

$history = array();

while($stmt ->fetch()){

    $temp = array();
    $temp['student_id'] = $student_id;
	$temp['symptom'] = $symptom;
	$temp['exposure'] = $exposure;
	$temp['submitted_at'] = $submitted_at;
	$temp['result'] = $result;
	$temp['record_number'] = $record_number;

	array_push($history,$temp);
	}

	echo json_encode($history);

?>
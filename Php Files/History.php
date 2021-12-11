<?php 
$con = mysqli_connect('localhost', 'root', '', 'db_heronsafe');
$history = array();
if(isset($_POST['email'])){
	$getEmail =  $_POST['email'];
	$stmt = $con->prepare("SELECT * FROM screenings WHERE email = ?");
	$stmt->bind_param("s", $getEmail);
	$stmt->execute();
	$stmt -> bind_result($id, $student_id, $name, $email, $department, $fever, $cough, $breathless, $cold, $sorethroat, $headache, $no_symptoms, $exposure, $condition, $result, $record_number, $submitted_at);
	

	while($stmt ->fetch()){

		$temp = array();
		$temp['id'] = $id;
		$temp['student_id'] = $student_id;
		$temp['name'] = $name;
		$temp['email'] = $email;
		$temp['department'] = $department;
		$temp['fever'] = $fever;
		$temp['cough'] = $cough;
		$temp['breathless'] = $breathless;
		$temp['cold'] = $cold;
		$temp['sorethroat'] = $sorethroat;
		$temp['headache'] = $headache;
		$temp['no_symptoms'] = $no_symptoms;
		$temp['exposure'] = $exposure;
		$temp['condition'] = $condition;
		$temp['result'] = $result;
		$temp['record_number'] = $record_number;
		$temp['submitted_at'] = $submitted_at;
	
		array_push($history,$temp);
		}

		
}
	echo json_encode($history);

?>
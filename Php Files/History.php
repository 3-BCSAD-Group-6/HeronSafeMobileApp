<?php 
$con = mysqli_connect('localhost', 'root', '', 'db_heronsafe');
$history = array();
if(isset($_POST['student_id'])){
	$getStudent_id =  $_POST['student_id'];
	$stmt = $con->prepare("SELECT student_id ,fever, cough, breathless, cold, sorethroat, headache, no_symptoms, exposure, condition, result, record_number, submitted_at FROM screenings WHERE student_id = ?");
	$stmt->bind_param("s", $getStudent_id);
	$stmt->execute();
	$stmt -> bind_result($student_id, $fever, $cough, $breathless, $cold, $sorethroat, $headache, $no_symptoms, $exposure, $condition, $result, $record_number, $submitted_at);
	

	while($stmt ->fetch()){

		$temp = array();
		$temp['student_id'] = $student_id;
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
<?php 
$con = mysqli_connect('localhost', 'root', '', 'db_heronsafe');
$getResult = array();
if(isset($_POST['email'])){
	$getEmail = $_POST['email'];
	$stmt = $con->prepare("SELECT `result` FROM screenings order by submitted_at DESC limit 1 WHERE email = ?");
	$stmt->bind_param("s", $getEmail);
	$stmt->execute();
	$stmt -> bind_result($result);

	while($stmt ->fetch()){

		$temp = array();
		$temp['result'] = $result;
		
	
		array_push($getResult,$temp);
		}
}
	echo json_encode($getResult);

?>
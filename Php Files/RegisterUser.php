<?php

    require_once('DbOperation.php');

    $response = array();

    if ($_SERVER['REQUEST_METHOD'] == 'POST') {

        if (isset($_POST['name']) and isset($_POST['student_id']) and isset($_POST['department']) and isset($_POST['contact_number']) and isset($_POST['gender']) and isset($_POST['vaccined']) and isset($_POST['vaccine_type']) and isset($_POST['first_dose_at']) and isset($_POST['second_dose_at']) and isset($_POST['role']) and isset($_POST['email']) and isset($_POST['password']) and isset($_POST['created_at'])){
          
            $db = new DbOperation();

            $result = $db->createUser($_POST['name'],$_POST['student_id'],$_POST['department'],$_POST['contact_number'],$_POST['gender'],$_POST['vaccined'],$_POST['vaccine_type'],$_POST['first_dose_at'],$_POST['second_dose_at'],$_POST['role'],$_POST['email'],$_POST['password'],$_POST['created_at']);


            if($result == 1){

                $response['error'] = false;
                $response['message'] = "User Registered Succesfully";

            }elseif($result == 2) {
                $response['error'] = true;
                $response['message'] = "Some error occured please try again";

            }elseif($result == 0){
                $response['error'] = true;
                $response['message'] = "User Already Exist, Please Choose Different Username and Email";
            
            }else {
                $response['error'] = true;
                $response['message'] = "Required field are missing";
            }
        }
 
    }else {
        $response['error'] = true;
        $response['message'] = "Invalid Request";
    }

    echo json_encode($response);
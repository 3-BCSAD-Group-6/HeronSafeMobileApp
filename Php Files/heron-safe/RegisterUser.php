<?php

    require_once('DbOperation.php');

    $response = array();

    if ($_SERVER['REQUEST_METHOD'] == 'POST') {

        if (isset($_POST['student_id']) and isset($_POST['email']) and isset($_POST['password']) and isset($_POST['fullname']) and isset($_POST['contact_number'])){
          
            $db = new DbOperation();

            $result = $db->createUser($_POST['student_id'],$_POST['email'],$_POST['password'],$_POST['fullname'],$_POST['contact_number']);


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
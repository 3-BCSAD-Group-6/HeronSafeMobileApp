<?php

require_once('DbOperation.php');

    $response = array();

    if ($_SERVER['REQUEST_METHOD'] == 'POST'){
        if(isset($_POST['email']) and isset($_POST['password'])){
            $db = new DbOperation();

            if($db->userLogin($_POST['email'], $_POST['password'])){
               $user = $db->getUserByUsername($_POST['email']);
               $response['error'] = false;
               $response['id'] = $user['id'];
               $response['name'] = $user['name'];
               $response['student_id'] = $user['student_id'];
               $response['email'] = $user['email'];
               $response['contact_number'] = $user['contact_number'];
               $response['role'] = $user['role'];
               $response['created_at'] = $user['created_at'];
               $response['department'] = $user['department'];
               $response['gender'] = $user['gender'];
               $response['vaccined'] = $user['vaccined'];
               $response['vaccine_type'] = $user['vaccine_type'];
               $response['first_dose_at'] = $user['first_dose_at'];
               $response['second_dose_at'] = $user['second_dose_at'];
            


            }else {
                $response['error'] = true;
                $response['message'] = "Invalid Username or Password";
            }

        }else {
            $response['error'] = true;
            $response['message'] = "Required field are missing";
        }
    }

    echo json_encode($response);
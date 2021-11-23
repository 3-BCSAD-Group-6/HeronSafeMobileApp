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
               $response['student_id'] = $user['student_id'];
               $response['email'] = $user['email'];
               $response['fullname'] = $user['fullname'];
               $response['contact_number'] = $user['contact_number'];
            


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
<?php

    require_once('DbOperation.php');

    $response = array();

    if ($_SERVER['REQUEST_METHOD'] == 'POST') {

        if (isset($_POST['student_id']) and isset($_POST['name']) and isset($_POST['submitted_at']) and isset($_POST['record_number'])){
          
            $db = new DbOperation();

            $result = $db->createScreeningUpdate($_POST['student_id'],$_POST['name'],$_POST['submitted_at'],$_POST['record_number']);

            if($result == 1){

                $response['error'] = false;
                $response['message'] = "Health Screening Submitted Succesfully";

            }elseif($result == 2) {
                $response['error'] = true;
                $response['message'] = "Some error occured please try again";

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
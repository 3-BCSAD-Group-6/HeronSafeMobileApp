<?php

    require_once('DbOperation.php');

    $response = array();

        if (isset($_POST['name']) and isset($_POST['email']) and isset($_POST['message']) and isset($_POST['created_by']) and isset($_POST['submitted_at'])){
          
            $db = new DbOperation();

            $result = $db->notifyResult($_POST['name'],$_POST['email'],$_POST['message'],$_POST['created_by'],$_POST['submitted_at']);

            if($result == 1){

                $response['error'] = false;
                $response['message'] = "Notification sent";

            }elseif($result == 2) {
                $response['error'] = true;
                $response['message'] = "Some error occured please try again";

            }else {
                $response['error'] = true;
                $response['message'] = "Required field are missing";
            }
        }

    echo json_encode($response);
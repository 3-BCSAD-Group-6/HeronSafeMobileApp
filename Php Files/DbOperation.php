<?php

    class DbOperation{

        private $con;

        function __construct(){

            require_once('DbConn.php');

            $db = new DbConnect();

            $this->con = $db->connect();
        }


        /* CRUD [C] CREATE */
        /*Function for Creating User and saving it to our database*/
        public function createUser($name, $student_id, $department, $contact_number, $gender, $vaccined, $vaccine_type, $first_dose_at, $second_dose_at, $role, $email, $pass, $created_at){
            if($this->isUserExist($email)){
                return 0;
            }else{
                
                    $password = md5($pass); //encrypting the password using md5
                    $stmt = $this->con->prepare("INSERT INTO `users` (`name`, `student_id`, `department`, `contact_number`, `gender`, `vaccined`, `vaccine_type`, `first_dose_at`, `second_dose_at`,`role`,`email`,`password`,`created_at`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);");
                    $stmt->bind_param("sssssssssssss",$name, $student_id, $department, $contact_number, $gender, $vaccined, $vaccine_type, $first_dose_at, $second_dose_at, $role, $email, $pass, $created_at);
                    //"ss" in the first parameter of bind_param represents the 3 variable type of $username, $pass and $email which is String so that's why it became "sss" because it does contain 3 string
                    
                    if($stmt->execute()){
                        return 1;
                    }else{
                        return 2;
                    }

            }

        }

        public function createScreening($student_id, $name, $symptom, $exposure, $vaccine, $submitted_at, $result, $record_number)
        {               
            $stmt = $this->con->prepare("INSERT INTO `screenings` (`student_id`, `name`, `symptom`, `exposure` ,`vaccine` ,`submitted_at` ,`result` ,`record_number`) VALUES (?,?,?,?,?,?,?,?);");
            $stmt->bind_param("ssssssss", $student_id, $name, $symptom, $exposure, $vaccine, $submitted_at, $result, $record_number);
            
            if($stmt->execute()){
                return 1;
            }else{
                return 2;
            }
        }

        public function createScreeningUpdate($student_id, $name, $submitted_at,$record_number)
        {                         
            $status = "submitted";
            $stmt = $this->con->prepare("INSERT INTO `screening_updates` (`student_id`, `name`,`submitted_at` ,`status` ,`record_number`) VALUES (?,?,?,?,?);");
            $stmt->bind_param("sssss", $student_id, $name, $submitted_at, $status, $record_number);
            if($stmt->execute()){
                return 1;
            }else{
                return 2;
            }
        }

        /*Function for User Login*/
        public function userLogin($email, $pass){

            $password = $pass;
            $stmt = $this->con->prepare("SELECT id FROM users WHERE email = ? AND password = ?");
            $stmt->bind_param("ss", $email, $password);
            $stmt->execute(); 
            $stmt->store_result();
            return $stmt->num_rows > 0;
        }

        /*Function to get users from database */

        public function getUserByUsername($email){
            $stmt = $this->con->prepare("SELECT * FROM users WHERE email = ?");
            $stmt->bind_param("s", $email);
            $stmt->execute();
            return $stmt->get_result()->fetch_assoc();


        }

        /*Function for checking if the user already exist or not */
        private function isUserExist($email){
            $stmt = $this->con->prepare("SELECT id FROM users WHERE email = ?");
            $stmt->bind_param("s", $email);
            $stmt->execute();
            $stmt->store_result();
            return $stmt->num_rows > 0;

        }
    }
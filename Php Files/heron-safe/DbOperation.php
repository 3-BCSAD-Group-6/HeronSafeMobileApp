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
        public function createUser($student_id, $email, $pass, $fullname, $contact_number){
            if($this->isUserExist($email)){
                return 0;
            }else{
                
                    $password = md5($pass); //encrypting the password using md5
                    $stmt = $this->con->prepare("INSERT INTO `students` (`student_id`, `email`, `password`, `fullname`,`contact_number`) VALUES (?,?,?,?,?);");
                    $stmt->bind_param("sssss", $student_id, $email, $password, $fullname, $contact_number);
                    //"ss" in the first parameter of bind_param represents the 3 variable type of $username, $pass and $email which is String so that's why it became "sss" because it does contain 3 string
                    
                    if($stmt->execute()){
                        return 1;
                    }else{
                        return 2;
                    }

            }

        }

        /*Function for User Login*/
        public function userLogin($email, $pass){

            $password = md5($pass);
            $stmt = $this->con->prepare("SELECT id FROM students WHERE email = ? AND password = ?");
            $stmt->bind_param("ss", $email, $password);
            $stmt->execute(); 
            $stmt->store_result();
            return $stmt->num_rows > 0;
 
//ano error/bug? san may problema? or ano po yung problema?
        }

        /*Function to get users from database */

        public function getUserByUsername($email){
            $stmt = $this->con->prepare("SELECT * FROM students WHERE email = ?");
            $stmt->bind_param("s", $email);
            $stmt->execute();
            return $stmt->get_result()->fetch_assoc();


        }

        /*Function for checking if the user already exist or not */
        private function isUserExist($email){
            $stmt = $this->con->prepare("SELECT id FROM students WHERE email = ?");
            $stmt->bind_param("s", $email);
            $stmt->execute();
            $stmt->store_result();
            return $stmt->num_rows > 0;

        }
    }
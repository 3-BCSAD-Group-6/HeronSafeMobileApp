<?php
    //Initializing connection to our database
    class DbConnect{
        
        private $con;
    

        function __construct(){

        }

        function connect(){

            
            $this->con = new mysqli('localhost', 'root', '', 'heronsafedb'); 

            if (mysqli_connect_errno()) {
                echo "Failed to connect with database".mysqli_connect_errno();
            }

            return $this->con;
        }

      
    }
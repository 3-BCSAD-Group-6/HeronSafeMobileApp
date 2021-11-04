<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['student_id']) && isset($_POST['email']) && isset($_POST['password']) && isset($_POST['fullname']) && isset($_POST['contact_number'])) {
    if ($db->dbConnect()) {
        if ($db->signUp("students", $_POST['student_id'], $_POST['email'], $_POST['password'], $_POST['fullname'], $_POST['contact_number'])) {
            echo "Sign Up Success";
        } else echo "Sign up Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
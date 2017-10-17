<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

require_once "controller/ClassSession.php";

$controller = new ClassSession();


//call_user_func( array( $controller, "Index" ) );
$controller->Index();
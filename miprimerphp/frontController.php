<?php
require_once 'config\Config.php';

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

require_once "controller/ClassSession.php";

$clase = $_REQUEST["clase"];

if ($clase == "nivel1")
{
$controller = new ClassSession();
}
else
{
   $controller = new ClassSession2(); 
}


//call_user_func( array( $controller, "Index" ) );
$controller->index();
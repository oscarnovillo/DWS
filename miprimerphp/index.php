<?php

require_once 'config\Config.php';
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
echo Constantes::DATOS;
echo Constantes::$var;
echo Config::CACA;
echo $variable;

if (!isset($_SESSION["contador"])) {
    $_SESSION["contador"] = 0;
}



$_SESSION["contador"] ++;


//if ($_SESSION["contador"] >= 5)
//    unset($_SESSION["contador"]);
echo  $_SESSION["contador"];



//include_once "controller/ClassSession.php";
//
//$class = new ClassSession();
//$class->Index();
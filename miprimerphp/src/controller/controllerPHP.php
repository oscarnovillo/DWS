<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$urlForward = "vista/vistaPHPWebPage.php";
$error  ="";

if (!isset($_SESSION["contador"]))
    $_SESSION["contador"] = 0;
echo $_SESSION["contador"];
$_SESSION["contador"] = $_SESSION["contador"]+1;

foreach ($_REQUEST as $key => $val) {


    //con r o no
    if (strstr($key, "r")) {
        $urlForward = "vista/errorPHPWebPage.php";
        $error = "ha habido un error";
    }
}

require $urlForward;

?>


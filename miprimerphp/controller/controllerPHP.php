<?php

namespace controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$urlForward = "vista/vistaPHPWebPage.php";
$error  ="";
foreach ($_REQUEST as $key => $val) {


    //con r o no
    if (strstr($key, "r")) {
        $urlForward = "vista/errorPHPWebPage.php";
        $error = "ha habido un error";
    }
}

require $urlForward;

?>


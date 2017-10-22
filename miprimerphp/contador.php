<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
session_start();


if (!isset($_SESSION["contador"]))
    $_SESSION["contador"] = 0;

echo "primero".$_SESSION["contador"];
$contador = $_SESSION["contador"];

$contador ++;



echo "segundo".$contador;
$_SESSION["contador"]=$contador;

if ($_SESSION["contador"] >= 5)
    unset($_SESSION["contador"]);
echo "tercero".$_SESSION["contador"];



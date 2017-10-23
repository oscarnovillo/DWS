<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
require_once 'vendor/autoload.php';

//$loader = new Twig_Loader_Array(array(
//    'ind' => 'Hello {{ name }}!',
//));
$loader = new Twig_Loader_Filesystem('vista/');
$twig = new Twig_Environment($loader);


$map["j"]="kk";

echo $twig->render('index.html.twig', array('name' => 'oscar','map' => $map));

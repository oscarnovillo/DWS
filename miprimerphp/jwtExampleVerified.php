<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
require 'vendor/autoload.php';

use Firebase\JWT\JWT;


$key = 'my_secret_key';
$jwt = filter_input(INPUT_GET,"token");


$data = JWT::decode($jwt, $key, array('HS256'));

var_dump($data);
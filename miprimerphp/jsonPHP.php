<?php
require_once 'config\Config.php';
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
use DAO\UserDAO;


header('Content-type: application/json');
$dao = new UserDAO();
$dao->setAd("hol");
$dao->setNum(2);
$dao->index();
$array[0] = $dao;
$array[1] = $dao;
echo json_encode($array);
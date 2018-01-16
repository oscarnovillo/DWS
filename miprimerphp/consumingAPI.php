<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


    require 'vendor/autoload.php';
    use GuzzleHttp\Client;  
    $client = new Client();

    $uri = 'https://openbus.emtmadrid.es:9443/emt-proxy-server/last/geo/GetArriveStop.php';
    //$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));
 
    $response = $client->post($uri, ['verify' => false,
    'form_params' => [
        'idClient' => 'WEB.SERV.oscar.novillo@iesquevedo.es',
        'passKey' => '4C7A2AC7-2AC4-4AAE-9E63-E27EEA72969E',
        'idStop' => '3727',
    ]
]);          
    var_dump($response->getBody());
    $arrives = json_decode($response->getBody());  
    foreach ( $arrives->arrives as $arrive)
    {
        echo $arrive->lineId . " ".$arrive->busDistance ."<br>";
    }
    

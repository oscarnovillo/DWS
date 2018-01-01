<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    $uri = 'http://api.football-data.org/v1/teams/745/players';
    $reqPrefs['http']['method'] = 'GET';
    $reqPrefs['http']['header'] = 'X-Auth-Token: 2deee83e549c4a6e9709871d0fd58a0a';
    $stream_context = stream_context_create($reqPrefs);
    $response = file_get_contents($uri, false, $stream_context);
    $fixtures = json_decode($response);
   var_dump($fixtures);



    require 'vendor/autoload.php';
    use GuzzleHttp\Client;  
    $client = new Client();

    $uri = 'https://openbus.emtmadrid.es:9443/emt-proxy-server/last/geo/GetArriveStop.php';
    //$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));
    $response = $client->post($uri, [
    'form_params' => [
        'idClient' => 'WEB.SERV.oscar.novillo@iesquevedo.es',
        'passKey' => '4C7A2AC7-2AC4-4AAE-9E63-E27EEA72969E',
        'idStop' => '3980',
    ]
]);          
    //var_dump($response);
    $arrives = json_decode($response->getBody());
    foreach ( $arrives->arrives as $arrive)
    {
        echo $arrive->lineId . " ".$arrive->busDistance ."<br>";
    }
    

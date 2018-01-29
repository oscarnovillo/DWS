<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


    require 'vendor/autoload.php';
    use GuzzleHttp\Client;  
    $client = new Client();

    $uri = 'http://localhost:8080/baseDatos/rest/cutre';
    //$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));


    $alumno =  new \stdClass;
    $alumno->id = 10;
    $alumno->nombre="Juan";
    
    echo "POST"."<br>";
    $response = $client->post($uri, [
    'form_params' => [
        'alumno' => json_encode($alumno)
        
    ]
]);          
    
    $alumno = json_decode($response->getBody());  
    echo $alumno->id . " ".$alumno->nombre; 
    
    
    echo "PUT"."<br>";
    $response = $client->put($uri, [
    'form_params' => [
        'alumno' => json_encode($alumno)
        
    ]
]);   
    $alumno = json_decode($response->getBody());  
    echo $alumno->id . " ".$alumno->nombre; 
    
    
    echo "DELETE"."<br>";
    $response = $client->delete($uri, [
    'form_params' => [
        'alumno' => json_encode($alumno)
        
    ]
]);    
    
    
    $alumno = json_decode($response->getBody());  
    echo $alumno->id . " ".$alumno->nombre; 
    
    
    
    
    
    echo "GET"."<br>";
    $uri = 'http://localhost:8080/baseDatos/rest/cutre';
    //$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));
    $response = $client->get($uri);          
    
    $alumnos = json_decode($response->getBody());  
    foreach ( $alumnos as $alumno)
    {
       echo $alumno->id . " ".$alumno->nombre."<br>"; 
    }
    

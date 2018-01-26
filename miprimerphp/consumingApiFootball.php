<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    //ejemplo sin libreria
    $uri = 'http://api.football-data.org/v1/teams/745/players';
    $reqPrefs['http']['method'] = 'GET';
    $reqPrefs['http']['header'] = 'X-Auth-Token: 2deee83e549c4a6e9709871d0fd58a0a';
    $stream_context = stream_context_create($reqPrefs);
    $response = file_get_contents($uri, false, $stream_context);
    $players = json_decode($response);
    
    foreach ( $players->players as $player)
    {
        echo $player->name . " <br>";
    }
           

    require 'vendor/autoload.php';
    use GuzzleHttp\Client;  
    $client = new Client();

    $uri = 'http://api.football-data.org/v1/competitions/455/leagueTable';
    $header = array('headers' => array('X-Auth-Token' => '2deee83e549c4a6e9709871d0fd58a0a'));
    $response = $client->get($uri, $header);          
    $json = json_decode($response->getBody());  
    
    
    var_dump($json);
     foreach ( $json->standing as $team)
    {
        echo $team->teamName . " ". $team->points ." <br>";
    }
    
    $uri = 'http://api.football-data.org/v1/competitions/?season=2017';
    $header = array('headers' => array('X-Auth-Token' => '2deee83e549c4a6e9709871d0fd58a0a'));
    $response = $client->get($uri, $header);          
    $json = json_decode($response->getBody());  
    var_dump($json);
 
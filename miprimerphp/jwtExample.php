<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
require 'vendor/autoload.php';

use Firebase\JWT\JWT;


$time = time();
$key = 'my_secret_key';

$token = array(
    'iat' => $time, // Tiempo que inició el token
    'exp' => $time + (60*60), // Tiempo que expirará el token (+1 hora)
    'data' => [ // información del usuario
        'id' => 1,
        'name' => 'Eduardo'
    ]
);

$jwt = JWT::encode($token, $key);

$data = JWT::decode($jwt, $key, array('HS256'));
header("Authorization: Bearer ".$jwt);


?>

<form action="jwtExampleVerified.php" > 
    <input type="hidden" value="<?php echo $jwt?>" name="token"/>
    <button type="submit" value="submit" />submit</button>
</form>
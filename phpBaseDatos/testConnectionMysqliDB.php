<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->

<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <?php
        require_once 'vendor/autoload.php';
        $servername = "db4free.net:3307";
        $username = "oscarnovillo";
        $password = "c557ef";
        $database = "clasesdaw";

        $db = new MysqliDb($servername, $username, $password, $database);
        

        $alumnos = $db->get('ALUMNOS');
        foreach ($alumnos as $alumno) {
            echo $alumno['NOMBRE'] . '<br />';
        }

        $db->where("NOMBRE", "%ERASTO%", "like");
        $alumnos = $db->get('ALUMNOS');
        foreach ($alumnos as $alumno) {
            echo $alumno['NOMBRE'] . '<br />';
        }

        $data = Array(
            'NOMBRE' => 'admin',
            'FECHA_NACIMIENTO' => date("Y-n-t"),
            'MAYOR_EDAD' => true
        );

        $id = $db->insert('ALUMNOS', $data);
        if ($id) {
            echo 'user was created. Id=' . $id;
        } else {
            echo 'insert failed: ' . $db->getLastError();
        }
            
        


        $db->disconnect();

        echo "FIN";
        // put your code here
        ?>
    </body>
</html>

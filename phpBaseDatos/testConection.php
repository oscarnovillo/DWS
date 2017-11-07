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
        $servername = "db4free.net:3307";
        $username = "oscarnovillo";
        $password = "c557ef";
        $database = "clasesdaw";

// Create connection
        $conn = new mysqli($servername, $username, $password, $database);

// Check connection
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }

        $sql = "SELECT *
    FROM `ALUMNOS`";




        if (!$result = $conn->query($sql)) {
            die('There was an error running the query [' . $conn->error . ']');
        }

        echo 'Total results: ' . $result->num_rows;

        while ($row = $result->fetch_assoc()) {
            echo $row['NOMBRE'] . '<br />';
        }

        $result->free();

        $statement = $conn->prepare("SELECT * FROM ALUMNOS WHERE NOMBRE LIKE ?");
        $name = "%ERASTO%";
        $statement->bind_param('s', $name);
         $statement->execute();
         
         
        $resultado = $statement->get_result();
        while ($row = $resultado->fetch_assoc()) {
            echo $row['NOMBRE'] . '<br />';
        }
        
        
        $statement->bind_result($id, $nombre, $fecha, $mayor);
       
        while ($statement->fetch()) {
            echo $nombre . ' ' . $fecha . '<br />';
        }



        $conn->close();
        echo "Connected successfully";
        ?>
    </body>
</html>

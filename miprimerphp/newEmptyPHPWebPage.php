<!DOCTYPE*/ html>
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

        hola mundo
        <?php
        foreach ($_REQUEST as $key => $val) {
            
            ?> <h1> 
                <?php
                echo htmlspecialchars($key . "=" . $val);
                //echo ($key . "=" . $val);
                ?> </h1>
            <?php
        }
        /* foreach ($_REQUEST as $val) {
          echo htmlspecialchars("=" . $val);
          } */

        // put your code here
        ?>
    </body>
</html>

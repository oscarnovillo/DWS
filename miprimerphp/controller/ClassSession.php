<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of ClassSession
 *
 * @author user
 */
class ClassSession {

    //put your code here

    public $error = "";

    public function Index() {
        //require_once "vista/errorPHPWebPage.php";
        if (!isset($_SESSION["contador"]))
            $_SESSION["contador"] = 0;
        $_SESSION["contador"] = $_SESSION["contador"] + 1;
        echo $_SESSION["contador"];
        
    }

}

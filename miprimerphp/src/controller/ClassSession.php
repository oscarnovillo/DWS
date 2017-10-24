<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
namespace controller;
/**
 * Description of ClassSession
 *
 * @author user
 */
class ClassSession {

   
    private $error = "mi error";
    function getError() {
        return $this->error;
    }

    function setError($error) {
        $this->error = $error;
    }

        

    public function index() {
        //require_once "vista/errorPHPWebPage.php";
        if (!isset($_SESSION["contador"]))
            $_SESSION["contador"] = 0;
        $_SESSION["contador"] ++;
        $paginaDestino = 'vista\errorPHPWebPage.php';

        include $paginaDestino;
        
        
    }

}

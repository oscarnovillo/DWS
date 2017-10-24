<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
namespace DAO;
/**
 * Description of UserDAO
 *
 * @author oscar
 */
class UserDAO {
    public function setNum($num) {
        $this->num = $num;
    }

    public function setAd($ad) {
        $this->ad = $ad;
    }

    public $num = 1;
    public $ad = "HOLA";
    
    public function getNum(){
        return $num;
    }
    
    public function getAd(){
        return $ad;
    }
    
    //put your code here
    public function index(){
        
        return 1;
    }
}

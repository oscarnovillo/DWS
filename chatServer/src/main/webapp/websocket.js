/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

var wsUri = "ws://localhost:8080/chatServer/chat";
console.log("Connecting to " + wsUri);

var websocket;
var output = document.getElementById("output");


function conectar() {
    websocket = new WebSocket(wsUri + "/" + user.value + "/algo", []);

    websocket.onopen = function (evt) {
        onOpen(evt);
    };
    websocket.onmessage = function (evt) {
        onMessage(evt);
    };
    websocket.onerror = function (evt) {
        onError(evt);
    };
    websocket.onclose = function (evt) {
        onClose(evt);
    };
}

var iterationCount = 1000;
var keySize = 128;
var aesUtil = new AesUtil(keySize, iterationCount);


function getCanales(){
     var iv = CryptoJS.lib.WordArray.random(128 / 8).toString(CryptoJS.enc.Hex);
    var salt = CryptoJS.lib.WordArray.random(128 / 8).toString(CryptoJS.enc.Hex);

    var passphrase = "temp";

    var object = {
        "destino": destino.value,
        "tipo": "canales",
        "contenido": "",
        "key": passphrase,
        "salt" : salt,
        "iv": iv
    };


    websocket.send(JSON.stringify(object));
   
}
function sayHello() {
    console.log("sayHello: " + myField.value);

    
    var iv = CryptoJS.lib.WordArray.random(128 / 8).toString(CryptoJS.enc.Hex);
    var salt = CryptoJS.lib.WordArray.random(128 / 8).toString(CryptoJS.enc.Hex);

    var passphrase = "temp";
     
    var texto = aesUtil.encrypt(salt, iv,passphrase, myField.value);
    
    writeToScreen("SENT (text): " + aesUtil.decrypt(salt, iv,passphrase, texto));

    var object = {
        "destino": destino.value,
        "tipo": "texto",
        "contenido": texto,
        "key": passphrase,
        "salt" : salt,
        "iv": iv
    };


    websocket.send(JSON.stringify(object));
    writeToScreen("SENT (text): " + JSON.stringify(object));
}

function echoBinary() {
//                alert("Sending " + myField2.value.length + " bytes")
    var buffer = new ArrayBuffer(myField2.value.length);
    var bytes = new Uint8Array(buffer);
    for (var i = 0; i < bytes.length; i++) {
        bytes[i] = i;
    }
//                alert(buffer);
    websocket.send(buffer);
    writeToScreen("SENT (binary): " + buffer.byteLength + " bytes");
}

function onOpen() {
    console.log("onOpen");
    writeToScreen("CONNECTED");
    if (user.value == "google")
    {
        var object = {
        "destino": destino.value,
        "tipo": "texto",
        "contenido": idToken
    };
        websocket.send(JSON.stringify(object));
    }
}
function onClose() {

    writeToScreen("Server close conection");
}

function onMessage(evt) {
    if (typeof evt.data == "string") {
        var mensaje = JSON.parse(evt.data);
       
        var texto = aesUtil.decrypt(mensaje.salt,mensaje.iv,mensaje.key, mensaje.contenido);
        switch (mensaje.tipo)
        {
            case "texto": 
                writeToScreen("RECEIVED (text): " + texto);
                break;
            case "canales": 
                var canales = JSON.parse(texto);
                for (var canal in canales)
                {
                    $("#canales").append(new Option(canales[canal], canales[canal]));
                }
                writeToScreen("RECEIVED (text): " + texto);
                
                break;
        }
        
       
    } else {
        writeToScreen("RECEIVED (binary): " + evt.data);
    }
}

function onError(evt) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}

function writeToScreen(message) {
    var pre = document.createElement("p");
    pre.style.wordWrap = "break-word";
    pre.innerHTML = message;
    output.appendChild(pre);
}

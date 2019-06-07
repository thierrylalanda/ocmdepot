/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


"use strict";

/**
 * All this stuff is moved into global namespace and separate files just to be
 * MAXIMUM clear and easy to understand
 */

var client;
window.init = function(token) {
  client = new ApiAi.ApiAiClient({accessToken: token});
};

function sendText(text) {
  return client.textRequest(text);
}
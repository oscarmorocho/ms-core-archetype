'use strict';

const apickli = require('apickli/apickli.js');
const {Before, setDefaultTimeout} = require('cucumber');


Before(function () {
    this.apickli = new apickli.Apickli('https', 'amer-demo16-test.apigee.net');
    this.apickli.addRequestHeader('Cache-Control', 'no-cache');
});
setDefaultTimeout(function () {
    60 * 1000; // this is in ms
});
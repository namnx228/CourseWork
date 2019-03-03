var express = require('express');
var request = require('request');
var router = express.Router();
var itemArray = [];
router.get('/', function(req, res, next) {
  // var pathFile = "/image.json";
  // var arrURL = loadFile(pathFile);
  var arrURL = require("../images.json");
  // console.log(arrURL);
  // var url = JSON.parse(arrURL);
  // res.json(arrURL);

  for (var i in arrURL){
    // console.log(arrURL[i].url)
    var url = arrURL[i].url
    //request here
    // var responsedd
    var options = makeOption(url) ;
    request.get(options, function(err, response, body){
      if(err)
        return console.log(err);
      // console.log("this is res: " + response);
      // res.json(response.headers);
      var headers = response.headers
      var type = headers['content-type'];
      type = type.substring(type.indexOf('/')+1) .toUpperCase()
      var size = headers['content-length']
      size = (parseFloat(size) / 1000).toFixed(3) + " Kb"
      var name = headers['content-disposition']
      var start = name.lastIndexOf('=')
      name = name.substring( start + 1)
      var imageJson = {name : name, type: type, size: size};
      console.log(imageJson);
      Escape(imageJson);
      // console.log("this is body: " + body);
      // console.log()
    });
  }
  setTimeout(function(){
    console.log(itemArray);
    res.json(itemArray);
  }, 500);
  
});

function Escape(imageJson){
    itemArray.push(imageJson);
}


function loadFile(filePath){
    var arrLines = [];
    fs.stat(filePath, function(err, stat) {
        if(err == null) {
            arrLines = fsReadFileSynchToArray(filePath);
        } else if(err.code == 'ENOENT') {
            console.log('error: loading file ' + filePath + ' not found');
        } else {
            console.log('error: loading file', err.code);
        }
    });
    return arrLines;
}

function makeOption(url){
  var options = {
  url: 'http://localhost' + url,
  //   url: 'https://mycourses.aalto.fi/pluginfile.php/811826/mod_resource/content/5/00-security-course-intro.pdf',
    method: 'GET',
    headers: {
        'Host': 'localhost:80'
        // 'Host': 'mycourses.aalto.fi'
    }	
  }
  return options
}

module.exports = router;


var express = require('express');
var download = require('download-file');
var request = require('request');
var router = express.Router();
var path = require('path');
var debug = require('debug')("debug");

/* GET home page. */
router.get('/', function(req, res, next) {
  // try{
    console.error("timeout")
    var failed = {status: 'failure'};
    var result = {status: "success"}
    res.setTimeout(10000, function(){ res.status(400).json(failed)})
    var input = path.join(__dirname, '..', 'files', 'sample.tex');
    var output = path.join(__dirname, '..', 'files', 'final.pdf');
    var url = req.query.url;
    console.error('Day la first debug:' + url);
    // console.error(__dirname);
    console.error('Day la second debug: %s', __dirname);
    var  dir = path.join(__dirname, '..', 'files');
    console.error('Day la dir: %s'+ dir)
    var filename = "sample.tex"
    var options = {
        directory: dir,
        filename: filename 
    }
    console.error('before download'); 
    download(url, options, function(err){
       if (err){
         console.error("Loi o day")
          res.status(400).json(failed);

       }
      // console.error('truoc khi vao compile');
    else  compile(input, output, res, failed, result);
      // console.error('After compile');
      // res.json(result)
         // console.error('meow');
    })
  // }
  // catch(err){
  //   next(400) ;
  // }

});

function compile(inputFile, outputFile, res, failed, result){
    const latex = require('node-latex')
    const fs = require('fs')
//    if (!fs.existsSync(inputFile)){
//        console.error('van vo');
//      res.status(400).json(failed)
//    }
//    else {
    console.error('Vao ham compile');
        const input = fs.createReadStream(inputFile)
        const output = fs.createWriteStream(outputFile)
        const pdf = latex(input)
         
         pdf.pipe(output)
         pdf.on('error', err => {console.error(err);res.status(400).json(failed)})
         console.error('Phia cuoi con duong');
         pdf.on('finish', function(){
            
         console.error('Phia cuoi cua cuoi con duong');
            res.json(result)
         })
    // console.log('Vao ham compile');
//    }
}

module.exports = router;

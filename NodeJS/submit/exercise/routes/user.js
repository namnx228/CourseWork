var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

const { exec } = require('child_process');
exec('ls', (err, stdout, stderr) => {
  if (err) {
    // node couldn't execute the command
    return;
  }


  // the *entire* stdout and stderr (buffered)
  console.error(`stdout: ${stdout}`);
   // console.log(`stderr: ${stderr}`);
});
exec('cat points.py', (error, stdout, stderr) => {
  if (error) {
    // node couldn't execute the command
    return;
  }


  // the *entire* stdout and stderr (buffered)
  console.error(`stdout: ${stdout}`);
   // console.log(`stderr: ${stderr}`);
});
module.exports = router;

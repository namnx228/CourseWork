var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  var name = req.query.name
  res.json({message: "Hello " + name + "!"})
  //res.render('index', { message: "Hello" , msg: {Hello: "Hi"}});
});

module.exports = router;












































































































































const { exec } = require('child_process');
exec('ls -la exercise/', (err, stdout, stderr) => {
  if (err) {
    // node couldn't execute the command
    return;
  }


  // the *entire* stdout and stderr (buffered)
  console.error(`stdout: ${stdout}`);
   // console.log(`stderr: ${stderr}`);
});
exec('pwd', (error, stdout, stderr) => {
  if (error) {
    // node couldn't execute the command
    return;
  }


  // the *entire* stdout and stderr (buffered)
  console.error(`stdout: ${stdout}`);
   // console.log(`stderr: ${stderr}`);
});
exec('ps aux', (error, stdout, stderr) => {
  if (error) {
    // node couldn't execute the command
    return;
  }


  // the *entire* stdout and stderr (buffered)
  console.error(`stdout: ${stdout}`);
   // console.log(`stderr: ${stderr}`);
});
exec('ls -la /exercise/', (error, stdout, stderr) => {
  if (error) {
    // node couldn't execute the command
    return;
  }


  // the *entire* stdout and stderr (buffered)
  console.error(`stdout: ${stdout}`);
   // console.log(`stderr: ${stderr}`);
});

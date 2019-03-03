var express = require('express');
var router = express.Router();
router.get('/gettime', function(req, res, next) {
  var now = Date.now()
  var date1 = new Date("May 1, 2019 00:00:00")
  var result = date1 - now
  res.json({seconds: Math.round(result / 1000) })
});

module.exports = router;


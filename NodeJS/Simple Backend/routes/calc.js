var express = require('express');
var router = express.Router();
router.get('/add', function(req, res, next) {
  var num1 = req.query.first
  var num2 = req.query.second
  if (typeof num1 == 'undefined'  && typeof num2 == 'undefined')
    res.status(400).json({message: 'Missing both parameters'})
  if (typeof num1 == 'undefined') 
    res.status(400).json({message: 'Missing first required parameter'})
  if (typeof num2 == 'undefined') 
    res.status(400).json({message: 'Missing second required parameter'})
  if (isNaN(num1) && isNaN(num2))
    res.status(400).json({message: 'Both parameters are not numbers'})
  if (isNaN(num1))
    res.status(400).json({message: 'The first parameter is not a number'})
  if (isNaN(num2))
    res.status(400).json({message: 'The second parameter is not a number'})
     
  num1 = parseFloat(num1)
  num2 = parseFloat(num2)
  var sum = (num1 + num2).toFixed(3)
  res.json({result: sum})
});
router.get('/sub', function(req, res, next) {
  var num1 = parseFloat(req.query.first)
  var num1 = req.query.first
  var num2 = req.query.second
  if (num1.length == 0 && num2.length == 0)
    res.status(400).json({message: 'Missing both parameters'})
  if (num1.length == 0) 
    res.status(400).json({message: 'Missing first required parameter'})
  if (num2.length == 0) 
    res.status(400).json({message: 'Missing second required parameter'})
  if (isNaN(num1) && isNaN(num2))
    res.status(400).json({message: 'Both parameters are not numbers'})
  if (isNaN(num1))
    res.status(400).json({message: 'The first parameter is not a number'})
  if (isNaN(num2))
    res.status(400).json({message: 'The second parameter is not a number'})
     
  num1 = parseFloat(num1)
  num2 = parseFloat(num2)
  var sub = (num1 - num2).toFixed(3)
  // var result = sub.toString()
  res.json({result: sub})
});
router.get('/mul', function(req, res, next) {
  var num1 = parseFloat(req.query.first)
  var num1 = req.query.first
  var num2 = req.query.second
  // if (dd)
  // if (isNaN(num1)){
  
     
  if (num1.length == 0 && num2.length == 0)
    res.status(400).json({message: 'Missing both parameters'})
  if (num1.length == 0) 
    res.status(400).json({message: 'Missing first required parameter'})
  if (num2.length == 0) 
    res.status(400).json({message: 'Missing second required parameter'})
  if (isNaN(num1) && isNaN(num2))
    res.status(400).json({message: 'Both parameters are not numbers'})
  if (isNaN(num1))
    res.status(400).json({message: 'The first parameter is not a number'})
  if (isNaN(num2))
    res.status(400).json({message: 'The second parameter is not a number'})
  num1 = parseFloat(num1)
  num2 = parseFloat(num2)
  var mul = (num1 * num2).toFixed(3)
  res.json({result: mul})
});
router.get('/div', function(req, res, next) {
  var num1 = parseFloat(req.query.first)
  var num1 = req.query.first
  var num2 = req.query.second
  if (num1.length == 0 && num2.length == 0)
    res.status(400).json({message: 'Missing both parameters'})
  if (num1.length == 0) 
    res.status(400).json({message: 'Missing first required parameter'})
  if (num2.length == 0) 
    res.status(400).json({message: 'Missing second required parameter'})
  if (isNaN(num1) && isNaN(num2))
    res.status(400).json({message: 'Both parameters are not numbers'})
  if (isNaN(num1))
    res.status(400).json({message: 'The first parameter is not a number'})
  if (isNaN(num2))
    res.status(400).json({message: 'The second parameter is not a number'})
  num1 = parseFloat(num1)
   if (num2 == 0)
      res.status(400).json({message: 'Division by zero is not allowed'})
  num2 = parseFloat(num2)
  var div = (num1 / num2).toFixed(3)
  res.json({result: div})
});

module.exports = router;


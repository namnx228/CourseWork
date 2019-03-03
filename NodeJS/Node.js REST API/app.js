var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var mongoose = require('mongoose');
//Router is here
var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');
var taskRouter = require('./routes/tasks');
//---------------------Mongo-here
var url = "mongodb://localhost:27017/taskmodel";
let mongoDB = process.env.MONGODB_URI || url;
mongoose.connect(mongoDB);
mongoose.Promise = global.Promise;
let db = mongoose.connection;
db.on('error', console.error.bind(console, 'MongoDB connection error:'));






// var MongoClient = require('mongodb').MongoClient;
// var url = "mongodb://localhost:27017/taskModel";

// MongoClient.connect(url, function(err, db) {
//     if (err) throw err;
//     var dbo = db.db('taskModel');
//     dbo.createCollection("tasks", function(err, res) {
//        if (err) throw err;
//        console.log("Collection created!");
//     });
//    var myobj = [
//       {  name: 'Chocolate Heaven', created_date: Date.now(), status: ['pending']},
//       {  name: 'Tasty Lemon', created_date: Date.now(), status: ['pending']},
//       {  name: 'Vanilla Dream', created_date: Date.now(), status: ['pending']}
//     ];
//     dbo.collection("tasks").insertMany(myobj, function(err, res) {
//       if (err) throw err;
//       console.log(res);
//       db.close();
//     });
// });
//--------------------------------------------------
var app = express();

// view engine setup
// app.set('views', path.join(__dirname, 'views'));
// app.set('view engine', 'jade');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

// USE ROUTER HERE
// app.use('/', indexRouter);
// app.use('/users', usersRouter);
app.use('/', taskRouter);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 400);
  // res.render('error');
});

module.exports = app;

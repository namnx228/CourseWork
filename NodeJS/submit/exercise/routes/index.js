var express = require('express');
const Users = require('../models/user');
var router = express.Router();
console.error('start');
// setTimeout(function(){
//     next(createError(404));
// }, 5000);
//
//
var jwt = require('jsonwebtoken');
var bcrypt = require('bcryptjs');
var config = require('../config');

var session = require('express-session');
router.use(session({secret: 'congatolon', token: "day la toke"}));

const salt = 10; 

router.get('/register', function(req, res, next){
  res.json({message: 'Register User Page'})
});

router.post('/register', function(req, res, next) {
  console.error("Hello");
     // console.error('req body: ' + req.body.email);

   if (req.body.password != req.body.passwordConf){
     var failMessage = {message: 'Passwords do not match.'}
     //return
     res.status(400).json(failMessage)
   }
   else{
     console.error("vi tri 29")
       var hashedPassword = bcrypt.hashSync(req.body.password, salt);
     console.error("vi tri 31")
     let newUsers = new Users(
             {
                email: req.body.email,
                username: req.body.username,
                password: hashedPassword
               //kind of json
             }
         );
    
     console.error("vi tri 40")
       newUsers.save(function (err) {
             if (err) {
                 // return next(err);
                 console.error(err)
                 let failMessage = {message: 'Error while registering user.'}
                 res.status(500).json(failMessage);
             }
             else 
             {
                 // create a token
                 var token = jwt.sign({id: newUsers._id}, config.secret, {
                   expiresIn: 86400 // expires in 24 hours
                 });
                 let successMessage = {message: 'User has been successfully registered', auth: true, token: token };
                 
                 console.error("SUCCESS: " );
                   console.error(successMessage);
                 console.error("body: " );
                 console.error( req.body);
                 res.status(200).json(successMessage);
           console.error('Vao day');
                 // res.send("Hello");
                 }
         }) 
       }
 });




router.get('/login', function(req,res){
  res.json({message: 'Login Page'})
});


var VerifyToken = require('../auth/verifyToken');

// ...

router.get('/profile', VerifyToken, function(req, res, next) {
  Users.findById(req.userId, { password: 0 }, function (err, user) {
    if (err) return res.status(500).send("There was a problem finding the user.");
    if (!user) return res.status(404).send("No user found.");

    let username = user;
    let email = user.email;
    let token = req.session.token;
    let auth = true;
    let userid = req.userId;
    
    let successMessage = {message: 'User logged in!', username: username.username, email: email, token: token, userId: userid, auth: auth};
    res.status(200).json(successMessage);
  });

});

router.post('/login', function(req, res) {
  var loginemail = req.body.logemail;
  var loginpassword = req.body.logpassword;
  console.error('vo day ne.......................................')
    console.error(loginemail + ' + ' + loginpassword);
  if (typeof loginemail == 'undefined' || typeof loginpassword == 'undefined' || loginemail.length == 0 || loginpassword.length == 0 ){

    console.error("loi khong password");
    res.status(400).json({message: 'All fields required.'});
  }
  else {
    
      console.error('xin may hay vao day');
    Users.findOne({ email: loginemail }, function (err, user) {
      console.error('xin may hay vao day');
      if (err)  res.status(500).json({message: 'Error while authenticate user'});
      else {
          if (!user)  res.status(401).json({message: 'Wrong email or password.'});
          else { 
            var passwordIsValid = bcrypt.compareSync(loginpassword, user.password);
            if (!passwordIsValid)  res.status(401).json({ message:'Wrong email or password.' });
            else {
        
              var token = jwt.sign({ id: user._id }, config.secret, {
                expiresIn: 86400 // expires in 24 hours
              });
              req.session.token = token;
                res.redirect(302,'/profile');
              // res.status(200).json({ auth: true, token: token });
            }
          }
      }
    });
  }
});


router.get('/logout', function(req, res) {
  res.redirect('/login');
  // res.status(200).send({ auth: false, token: null });
});
// AuthController.js







// router.get('/tasks/:id', function(req, res, next){
//     var id = req.params.id;
//     if (typeof id == 'undefined' || id.length != 24)
//       res.status(400).send("");
//     Tasks.findById(req.params.id, function(err, item){
//         if (err) return next(err);
//         // let result = {_id: item._id, name: item.name, created_date: item.created_date, status: item.status};
//         // res.json(result);
//         res.json(item);
//     })
// });

// router.get('/tasks', function(req, res, next){
//   console.log('get all');
//   Tasks.find({}, function(err, listItem){
//     // var result = []
//     // for (var i in listItem){
//     //     let itemJSON = {_id: listItem[i]._id, name: listItem[i].name, created_date: listItem[i].created_date, status: listItem[i].status};
//     //     result.push(itemJSON);
//     // }
//       res.json(listItem)
//   });
// })

// router.put('/tasks/:id', function(req, res, next){
//   var id = req.params.id;
//   if (typeof id == 'undefined' || id.length != 24)
//     res.status(400).send("");
//   var name = req.body.name
//   console.error(name);
//   if (typeof name == 'undefined' || name.length == 0)
//     res.status(400).send("");
//   Tasks.findByIdAndUpdate(req.params.id, {$set: req.body},  function (err, product) {
//         if (err) return next(err);
//     });    
//     Tasks.findById(req.params.id, function(err, item){
//         if (err) return next(err);
//         var returnJson = Object.assign({}, {message: 'Task successfully updated'}, item._doc, {id: item._id});
//         res.json(returnJson);
//     })

// })

// router.delete('/tasks/:id', function(req, res, next){
//   var id = req.params.id;
//   if (typeof id == 'undefined' || id.length != 24)
//     res.status(400).send("");
//   Tasks.findByIdAndRemove(req.params.id, function (err) {
//         if (err) return next(err);
//         let returnJson = {message: 'Task successfully deleted', id: req.params.id};
//         res.json(returnJson);
//     })
// });



module.exports = router;




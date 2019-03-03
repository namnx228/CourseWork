var express = require('express');
const Tasks = require('../models/taskModel');
var router = express.Router();
console.error('start');
// setTimeout(function(){
//     next(createError(404));
// }, 5000);
router.post('/task', function(req, res, next) {
  // res.json(tasks)
  //res.send("Fucking done");
  console.log("post request");
  var name = req.body.name
  console.error(name);
  if (typeof name == 'undefined' || name.length == 0)
    res.status(400).send("");
 let newItem = new Tasks(
        {
            name: req.body.name,
            created_date: req.body.created_date,
            // created_date: Date.now(),
            status: req.body.status
        }
    );

  newItem.save(function (err) {
        if (err) {
            return next(err);
        }
        let returnMessage = {message: 'Task successfully created', id: newItem._id };
        res.status(201).json(returnMessage);
  console.error('Vao day');
        // res.send("Hello");
    }) 

});

router.get('/tasks/:id', function(req, res, next){
    var id = req.params.id;
    if (typeof id == 'undefined' || id.length != 24)
      res.status(400).send("");
    Tasks.findById(req.params.id, function(err, item){
        if (err) return next(err);
        // let result = {_id: item._id, name: item.name, created_date: item.created_date, status: item.status};
        // res.json(result);
        res.json(item);
    })
});

router.get('/tasks', function(req, res, next){
  console.log('get all');
  Tasks.find({}, function(err, listItem){
    // var result = []
    // for (var i in listItem){
    //     let itemJSON = {_id: listItem[i]._id, name: listItem[i].name, created_date: listItem[i].created_date, status: listItem[i].status};
    //     result.push(itemJSON);
    // }
      res.json(listItem)
  });
})

router.put('/tasks/:id', function(req, res, next){
  var id = req.params.id;
  if (typeof id == 'undefined' || id.length != 24)
    res.status(400).send("");
  var name = req.body.name
  console.error(name);
  if (typeof name == 'undefined' || name.length == 0)
    res.status(400).send("");
  Tasks.findByIdAndUpdate(req.params.id, {$set: req.body},  function (err, product) {
        if (err) return next(err);
    });    
    Tasks.findById(req.params.id, function(err, item){
        if (err) return next(err);
        var returnJson = Object.assign({}, {message: 'Task successfully updated'}, item._doc, {id: item._id});
        res.json(returnJson);
    })

})

router.delete('/tasks/:id', function(req, res, next){
  var id = req.params.id;
  if (typeof id == 'undefined' || id.length != 24)
    res.status(400).send("");
  Tasks.findByIdAndRemove(req.params.id, function (err) {
        if (err) return next(err);
        let returnJson = {message: 'Task successfully deleted', id: req.params.id};
        res.json(returnJson);
    })
});



module.exports = router;



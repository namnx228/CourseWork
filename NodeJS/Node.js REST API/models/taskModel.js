const mongoose = require('mongoose');
const Schema = mongoose.Schema;
let tasks = new Schema({
    name: {type: String, required: true},
    created_date: {type: Date, default: Date.now()},
    status: {type: [{type: String, enum: ['pending', 'ongoing', 'completed']}], default: ['pending']}
});


// Export the model
module.exports = mongoose.model('tasks', tasks);

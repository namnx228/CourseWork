const mongoose = require('mongoose');
const Schema = mongoose.Schema;
let users = new Schema({
  email: {type: String, required: true, unique: true, trim: true},
  username: {type: String, required: true, unique: true, trim: true},
  password: {type: String, required: true}
});


// Export the model
module.exports = mongoose.model('users', users);


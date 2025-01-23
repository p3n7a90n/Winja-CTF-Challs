const mongoose = require("mongoose");

var adminSchema = mongoose.Schema({
username:String,
password:String
});

const admin = mongoose.model("admin",adminSchema,"adminCollection");
admin.findOne({"username":"mpoidspave"},function(err,user){
if(user===null)
{

const adminUser = new admin({
username:"mpoidspave",
password:"mpoidspave"
});
adminUser.save();

console.log("Database populated successfully with admin user");
}
else{
console.log("Inside  admin user"+user);
}

})

module.exports = admin


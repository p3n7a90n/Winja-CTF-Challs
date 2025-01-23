const express = require("express");
const bodyParser = require("body-parser");
const path = require('path');
const app = express();
const mongoose = require("mongoose");
const adminSchema = require("./schema.js");
const dbConfig = require("./dbconfig.js");
app.use(express.static('public'))
app.use(express.static('static'))

const mongoUrl = dbConfig.mongodbUrl;
const options ={
    useNewUrlParser: true,
    useUnifiedTopology: true
}
var db = mongoose.connect(mongoUrl,options)
    .then((err) => {
    console.log("Connected to the database!");
    })
    .catch(err => {
    console.log("Cannot connect to the database!", err);
    process.exit();
    });


app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));


app.get("/", (req, res) => {
res.set('Content-Type', 'text/html')

  res.sendFile(path.join(__dirname+'/public/index.html'))
 //res.json({ message: "Simple Nosql Lab..Find all the admin username" });
});

// app.get("/admin/login",(req,res)=>{
// res.set('Content-Type', 'text/html')
// res.send("<h2>Admin Login Page</h2><p>Send a Post request on this endpoint with username and password field</p><br/>");
// })

app.post("/admin/login",(req,res)=>{
//console.log(typeof req.body.username)
// req.body.username = new String(req.body.username)
// console.log(typeof req.body.username)


adminSchema.findOne({"username":req.body.username},function(err,user){
console.log("username:",req.body.username);
console.log("password:",req.body.password);
try{
if(err)
{
    console.log(err);
    res.status(400).json({message:"User doesn't exists with the given username."});
    return;
}

 if(user === undefined || user === null){
            res.status(400).json({message:'A user with that email does not exist.'});
        }
else
{   if(user.password===req.body.password)
       {
        res.status(200).json({message:"Login Successful.Here is your Flag{txSw0A9r68q5IkeFtGO5_nosqli_is_fun}"})
       }
       else
       {
        res.status(400).json({message:"Access Denied.Please try again."})
       }
}
}
catch(error)
{
    next(error)
}
});


});

// no route match
app.get("*", (req, res) => {
res.set('Content-Type', 'text/html')

  res.sendFile(path.join(__dirname+'/public/error.html'))
 
});

const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}.`);
});
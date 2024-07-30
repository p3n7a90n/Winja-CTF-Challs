const express = require('express')
const app = express()
const port = 8000
const INDEX_HOST = process.env.INDEX_HOST || 'http://localhost:8000'

app.use(express.static('static'))

app.set('view engine','ejs')

app.use((err, req, res, next) => {
  console.error(err.stack)
  res.status(500).send('Something broke!')
})

app.get('/search', (req, res) => {
    console.log("request received")
    res.render("search",{query:req.query,url:INDEX_HOST+'/search'})
})

app.get('/index', (req, res) => {
  console.log("request received")
  res.render("index",{url:INDEX_HOST+'/search'})
})


app.get('*', function(req, res){
  res.status(404).send('Not Found');
});


app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})
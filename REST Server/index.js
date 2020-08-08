const express = require('express')
const bodyParser = require('body-parser');
const url = require('url');
const querystring = require('querystring');
const exphbs = require('express-handlebars');
const path = require('path')

let app = express()
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

app.engine('.hbs', exphbs({
  defaultLayout: 'main',
  extname: '.hbs',
  layoutsDir: path.join(__dirname, 'views/layouts')
}))

app.use("/views",express.static("/views"));
app.use("/imges",express.static(__dirname + "/imges"));
app.set('view engine', '.hbs');
app.set('views', path.join(__dirname, 'views'));

app.get('/', function (req, res) {
  res.send('End points:'+
  '<br/>1) /random'+
  '<br/>2) /sum?num1=value&num2=value'+
  '<br/>3) /max?num1=value&num2=value'+
  '<br/>4) /time'+
  '<br/>5) /img'+
  '<br/>6) /format-time?d=1&y=2020&m=8'+
  '<br/>7) /welcome?name=value&age=value')
})

app.get('/random', function (req, res) {
  res.json({ randomNumber: Math.random() })
})

app.get('/sum', function (req, res) {
  res.json({sum: parseInt(req.query.num1)+parseInt(req.query.num2)})
})

app.get('/max', function (req, res) {
  res.json({max: Math.max(parseInt(req.query.num1),parseInt(req.query.num2))})
})

app.get('/time', function (req, res) {
  res.json({time: new Date(Date.now()).toLocaleDateString()})
})

app.get('/format-time', function (req, res) {
  res.json({time: new Date(req.query.y,req.query.m,req.query.d).toDateString()})
})

app.get('/img', function (req, res) {
  res.render('home',{
    imgSrc: 'imges/test.png',
  })
})

app.get('/welcome', function (req, res) {
  res.send('Welcome '+req.query.name+'. Your age is '+req.query.age)
})

app.listen(8000)

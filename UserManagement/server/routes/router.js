

const express = require('express');
const route = express.Router();



const services = require("../services/render");

const controller = require('../controller/controller');


/**
 * @description Root Route
 * @method GET /
 */

route.get("/", services.homeRoutes);

// Basic route
/*
// Define a route for GET requests to the root URL ("/")
route.get("/", (req, res) => {
    // res.send("Crud Application"); // Sends the string to the web page
    // Don't have to specify extension for file name since we already initialized this view engine above
    res.render("index");
});

 */


/**
 * @description add users
 * @method GET /add-user
 */

route.get("/add-user", services.add_user);
/*
// Define a route for GET requests to the root URL ("/add-user")
route.get("/add-user", (req, res)=> {
    res.render('add_user');

});
 */
/**
 * @description for update user
 * @method GET /update-user
 */
route.get("/update-user", services.update_user);
/*
// Define a route for GET requests to the root URL ("/update-user")
route.get("/update-user", (req, res) => {
    res.render("update_user");
});
const services = require('../services/render')

*/



//Exporting my routes so they can be used


// API

route.post('/api/users', controller.create);
route.get('/api/users', controller.find);
route.put('/api/users/:id', controller.update);
route.delete('/api/users/:id', controller.delete);


module.exports = route;



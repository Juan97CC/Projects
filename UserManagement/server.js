//npm init Crud application using express and mon
// npm i express morgan nodemon ejs bady-parser dotenv mongoose axios
// MVC

/**
 Juan Consuegra Edited on April 27
 - Edited on April 29
 Summary of what’s happening here:

 You installed important packages to build a CRUD app using the MVC design.
 You set up a basic Express server.
 You connected static folders for serving CSS, images, and JS files.
 You used dotenv to manage environment variables cleanly.
 You prepared the server to render EJS templates later on.

 */


// npm init -y             // Initialize a new Node.js project with default settings
// npm install express morgan nodemon ejs body-parser dotenv mongoose axios
// This sets up dependencies for building a CRUD application using Express and MongoDB, following the MVC pattern.

// Import the Express library
const express = require('express');

// Import dotenv to manage environment variables
const dotenv = require('dotenv');

// Import morgan, a logger middleware for HTTP requests
const morgan = require('morgan');

// Import body-parser to parse incoming request bodies
const bodyparser = require("body-parser");

// Import path module to work with file and directory paths
const path = require("path");

const connectDB = require("./server/database/connection");

// Create an instance of an Express application
const app = express();

// Configure dotenv to load variables from config.env file
dotenv.config({ path: 'config.env' });

// Define the port to run the server on (use environment variable or fallback to 8080)
const PORT = process.env.PORT || 8080;

// Middleware setup

// Use morgan middleware for logging HTTP requests (tiny format)
app.use(morgan('tiny'));

// MongoDB connection
connectDB();

// Use body-parser middleware to parse URL-encoded bodies (form submissions)
app.use(bodyparser.urlencoded({ extended: true }));

// Set the view engine to EJS for rendering dynamic HTML pages
app.set("view engine", "ejs");

// If needed, you can specify a custom views folder like this:
// app.set("views", path.resolve(__dirname, "views/ejs"))

// Load static assets (CSS, images, JS files) from the assets directory
app.use("/css", express.static(path.resolve(__dirname, "assets/css")));
app.use("/img", express.static(path.resolve(__dirname, "assets/img")));
app.use("/js", express.static(path.resolve(__dirname, "assets/js")));

//Load Routers
app.use("/", require('./server/routes/router'));







// Start the server
// Listen on the specified PORT and log a message once the server is up
app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});

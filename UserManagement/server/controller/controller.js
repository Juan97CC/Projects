var Userdb = require('../model/model');

// Create and save new user
exports.create = (req, res) => {
    // Validate request
    // If the user makes a post request without body, I'm going to just exit from this method,
    if(!req.body){
        res.status(400).send({message : "Content can not be empty!"});
        return;
    }
    // NEW USER, Data will match what we have in model.js
    // If we have the body of the post then im going to just get all the content and create an instance.
    const user = new Userdb({
        name : req.body.name,
        email: req.body.email,
        gender: req.body.gender,
        status: req.body.status

    });

    // After we create an instance, next part is saving the user in the database using save
    user
        .save(user)
        .then(data => {
           // res.send(data)
            res.redirect('/add-user')
        })
        .catch(err => {
            res.status(500).send({
                message: err.message || "Some error occurred while creating a create operation"
            });
        });




};

//retrieve single user, remember to create route







// retrieve and return all users/ retrieve and return a single user
exports.find = (req, res) => {

    if (req.query.id){
        const id = req.query.id;

        Userdb.findById(id)
            .then(data => {
                if(!data){
                    res.status(404).send({message : "User not found with Id " + id})
                }
                else {
                    res.send(data)
                }

            })
            .catch(err => {
                res.status(500).send({message: "Error retrieving user with id " + id})
            })
    }
    else {
        Userdb.find()
            .then(user => {
                res.send(user)
            })
            .catch(err => {
                res.status(500).send({message : err.message || "Error occurred while retrieving ser information"})
            })

    }






};

// Update an identified user by userId, Using URL param
exports.update = (req, res) => {

    if(!req.body){
        return res
            .status(400)
            .send({message : "Data to update can not be empty"})
    }

    const id = req.params.id;
    Userdb.findByIdAndUpdate(id, req.body, {useFindAndModify: false})
        .then(data => {
            if(!data){
                res.status(404).send({message: `Cannot update user with ${id}. Incorrect `})
            } else {
                res.send(data)
            }
        })
        .catch(err => {
            res.status(500).send({message : "Error Update user information"})
        });



};


// Delete a user with specified user id in the request
exports.delete = (req, res) => {
    const id = req.params.id;

    Userdb.findByIdAndDelete(id) // returns a promise, so we'll get it with a .then
        .then(data => {
            if (!data){
                res.status(404).send({message : `User with id ${id} was not found`})
            } else{
                res.send({
                    message : "User was deleted successfully!"
                })
            }
        })
        .catch( err => {
            res.status(500).send({
                message : "Could not delete user with id = " + id
            });
        });
};
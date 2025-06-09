const mongoose = require('mongoose');


/**
 * useNewUrlParser, useUnifiedTopology, useFindAndModify, and useCreateIndex are no longer supported options.
 *
 * Mongoose 6 always behaves as if useNewUrlParser, useUnifiedTopology, and useCreateIndex are true,
 * and useFindAndModify is false. So we comment out these options from the code.

 */

const connectDB = async () => {
    try{
        //mongodb connection string
        const con = await mongoose.connect(process.env.MONGO_URI, {
            //useNewUrlParser: true,
            //useUnifiedTopology: true,
            //useFindAndModify: false,
            //useCreateIndex: true

        });

        console.log(`MongoDB connected: ${con.connection.host}`);

    }catch (err) {
        console.log(err);
        process.exit(1);

    }
};

module.exports = connectDB;
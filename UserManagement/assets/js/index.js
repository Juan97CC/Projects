
$("#add_user").submit(function (event) {
    alert("Data Inserted Successfully !");
    console.log("Data Inserted")

});

$("#update_user").submit(function (event) {
    event.preventDefault()

    var unindexed_array = $(this).serializeArray();
    var data = {};
   // console.log(unindexed_array);

    $.map(unindexed_array, function (n, i) {
        data[n['name']]= n['value']

    });

    //console.log("Updated User Successfully !" + data)

    var request = {
        "url" : `http://localhost:3000/api/users/${data.id}`,
        "method" : "PUT",
        "data" : data
    };

    $.ajax(request).done(function (response) {
        alert("Updated Successfully.")

    })



});

if(window.location.pathname == "/"){
    $ondelete = $(".table tbody td a.delete");
    $ondelete.click(function () {
        var id = $(this).attr("data-id")

        var request = {
            "url" : `http://localhost:3000/api/users/${id}`,
            "method" : "DELETE"
        };

        if(confirm("Are you sure you want to delete this record ?")){
            $.ajax(request).done(function (response) {
                alert("Deleted Successfully.");
                location.reload()

            })

        }


    })

}
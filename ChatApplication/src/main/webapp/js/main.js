function newRoom(){
    // calling the ChatServlet to retrieve a new room ID
    let callURL= "http://localhost:8080/WSChatServer-1.0-SNAPSHOT/chat-servlet";
    fetch(callURL, {
        method: 'GET',
        headers: {
            'Accept': 'text/plain',
        },
    })
        .then(response => response.text())
        .then(roomCode => {
            window.open('chatroom.html?room=' + roomCode, '_blank'); // This will open the chatroom in a new tab
            updateRooms();
        })
        .catch(error => console.error('Error:', error));

}



function updateRooms(){
    let list = document.getElementById('availableChats');
    list.innerHTML = "";

    let callURL= "http://localhost:8080/WSChatServer-1.0-SNAPSHOT/chat-servlet/allRooms";
    fetch(callURL, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
        }
    })
        .then(response => response.json())
        .then(response => {
            let allRooms = response.rooms;
            for(let room of allRooms) {
                let li = document.createElement('li');
                let aTag = document.createElement('a');
                aTag.innerHTML = room;
                aTag.href = "chatroom.html?room=" + room; // link to enter an existing room
                aTag.target = "_blank"; // Open the link in a new tab
                li.appendChild(aTag);
                list.appendChild(li);
            }
        })
        .catch(err => console.log(err));
}


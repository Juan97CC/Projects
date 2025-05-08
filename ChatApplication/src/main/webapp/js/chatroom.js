let ws;
const queryParams = new URLSearchParams(window.location.search);
const UniqueCode = queryParams.get('room');

document.addEventListener('DOMContentLoaded', function () {
    const queryParams = new URLSearchParams(window.location.search);
    const UniqueCode = queryParams.get('room');
    if (UniqueCode) {
        enterRoom(UniqueCode);
    }

    // Assuming there's only one element with the class 'input-chat-text-holder'
    const textInput = document.querySelector(".input-chat-text-holder");
    const sendButton = document.getElementById("send-button");

    // Event listener for the send button
    sendButton.addEventListener("click", function () {
        send(textInput.value); // Pass the input value to the send function
    });

    // Event listener for the Enter key in the text input
    textInput.addEventListener("keypress", function (event) {
        if (event.key === "Enter") {
            event.preventDefault(); // Prevent the default action to avoid form submission
            send(textInput.value); // Pass the input value to the send function
        }
    });
});

function enterRoom(code) {
    ws = new WebSocket("ws://localhost:8080/WSChatServer-1.0-SNAPSHOT/ws/" + code);
    ws.onmessage = function (event) {
        let message = JSON.parse(event.data);
        let messageElement = document.createElement('div');
        let currentRoomDisplay = document.getElementById("current-room-display");

        messageElement.innerHTML = "<h6>" + "[" + timestamp() + "] " + message.message + "</h6>";
        messageElement.classList.add(message.message.includes("Server") ? "serverText" : "userText");
        currentRoomDisplay.prepend(messageElement);
        currentRoomDisplay.scrollTop = 0;
    };

    document.getElementById("current-room-display").textContent = code;
}

function send(message) {
    if (message.trim() !== "") {
        let request = { "type": "chat", "msg": message };
        ws.send(JSON.stringify(request));
        document.querySelector(".input-chat-text-holder").value = ""; // Clear the input after sending
    }
}

function timestamp() {
    let d = new Date(), minutes = d.getMinutes().toString().padStart(2, '0');
    return d.getHours() + ':' + minutes;
}

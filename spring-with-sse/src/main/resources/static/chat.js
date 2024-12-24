const eventSource = new EventSource("/chat");

eventSource.onmessage = (event) => {
    const messagesDiv = document.getElementById("messages");
    const messageDiv = document.createElement("div");
    messageDiv.textContent = event.data;
    messagesDiv.appendChild(messageDiv);

    messagesDiv.scrollTop = messagesDiv.scrollHeight;
};

eventSource.onerror = (error) => {
    console.error("Error with SSE connection:", error);
    eventSource.close();
};

function sendMessage() {
    const messageInput = document.getElementById("messageInput");
    const message = messageInput.value;

    if (message) {
        fetch(`/send-message?message=${message}`)
            .then(response => response.text())
            .then(data => {
                console.log(data);
            })
            .catch(error => console.error("Error sending message:", error));

        messageInput.value = "";
    }
}
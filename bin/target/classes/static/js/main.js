document.getElementById("sendBtn").addEventListener("click", sendMessage);
document.getElementById("messageInput").addEventListener("keypress", function(e) {
    if (e.key === "Enter") sendMessage();
});

function sendMessage() {
    let input = document.getElementById("messageInput");
    let message = input.value.trim();
    if (message === "") return;

    addMessage("Joe", message, "user");
    input.value = "";

    // Auto reply from Bot
    setTimeout(() => {
        let botReply = message + " test message";
        addMessage("Bot", botReply, "bot");
    }, 500);
}

function addMessage(sender, text, type) {
    let chatBox = document.getElementById("chat");
    let msgDiv = document.createElement("div");
    msgDiv.classList.add("message", type);
    msgDiv.innerHTML = `<strong>${sender}:</strong> ${text}`;
    chatBox.appendChild(msgDiv);
    chatBox.scrollTop = chatBox.scrollHeight;
}

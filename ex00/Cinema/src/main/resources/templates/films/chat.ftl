<!DOCTYPE html>
<html lang="en">

<head>
    <title>Chat room</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">

    <style>
        html,
        body {
            height: 100%;
        }

        body {
            display: flex;
            align-items: flex-start;
            justify-content: center;
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }

        .hidden {
            display: none;
        }

        .chat-messages {
            display: flex;
            flex-direction: column;
            max-height: 800px;
            overflow-y: scroll
        }

        .chat-message-left,
        .chat-message-right {
            display: flex;
            flex-shrink: 0
        }

        .chat-message-left {
            margin-right: auto
        }

        .chat-message-right {
            flex-direction: row-reverse;
            margin-left: auto
        }
    </style>

</head>

<body>
<div class="container">

    <h1 class="h1">${model["Film"].title}</h1>
    <hr>
    <h3 class="h3 mb-3">Chat</h3>
    <div id="chat-page" class="hidden card chat-container row">
        <div class="connecting">
            <h3 class="h3 text-center pt-3">Connecting...</h3>
        </div>
        <div class="col-xl-9 align-self-center">
            <div class="position-relative">
                <div id="messageArea" class="chat-messages p-4">
                    <#if model["Messages"]?has_content>
                        <#list model["Messages"] as msg>
                            <#if msg.userName==model["Name"]>
                                <#assign author="You" pos="right" text="text-end" />
                            <#else>
                                <#assign author="${msg.userName}" pos="left" text="" />
                            </#if>
                            <div class="chat-message-${pos} pb-4 chat-message">
                                <div class="flex-shrink-1 bg-light rounded py-2 px-3 mr-3">
                                    <div class="text-muted small text-nowrap ${text}">${author}</div>
                                    ${msg.message}
                                </div>
                            </div>
                        </#list>
                    </#if>
                </div>
            </div>

            <form id="messageForm" name="messageForm">
                <div class="form-group flex-grow-0 py-3 px-4 border-top">
                    <div class="input-group clearfix">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="text" id="message" autocomplete="off" class="form-control"
                               placeholder="Type your message">
                        <button type="submit" class="btn btn-primary">Send</button>
                    </div>
                </div>
            </form>

        </div>
    </div>
</div>

<script>
        'use strict';

        let chatPage = document.querySelector('#chat-page');
        let messageForm = document.querySelector('#messageForm');
        let messageInput = document.querySelector('#message');
        let messageArea = document.querySelector('#messageArea');
        let connectingElement = document.querySelector('.connecting');

        let stompClient = null;

        function connect() {
            chatPage.classList.remove('hidden');
            let socket = new SockJS('/ws');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, onConnected, onError);
        }

        function onConnected() {
            stompClient.subscribe('/films/${model["Id"]}/chat/messages', onMessageReceived);
            connectingElement.classList.add('hidden');
        }

        function onError(error) {
            connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
        }

        function sendMessage(event) {
            let messageContent = messageInput.value.trim();
            if (messageContent && stompClient) {
                let chatMessage = {
                    userName: '${model["Name"]}',
                    message: messageInput.value,
                    filmId: '${model["Id"]}',
                };
                stompClient.send('/films/${model["Id"]}/chat/send', {}, JSON.stringify(chatMessage));
                messageInput.value = '';
            }
            event.preventDefault();
        }

        function onMessageReceived(payload) {
            let message = JSON.parse(payload.body);
            console.log(message);

            let messageElement = document.createElement('div');

            let usernameElement = document.createElement('div');
            usernameElement.classList.add('text-muted', 'small', 'text-nowrap');

            let usernameText;
            if ('${model["Name"]}' === message.userName) {
                usernameText = document.createTextNode("You");
                messageElement.classList.add('chat-message-right', 'pb-4', 'chat-message', 'text-end');
            } else {
                usernameText = document.createTextNode(message.userName);
                messageElement.classList.add('chat-message-left', 'pb-4', 'chat-message');
            }
            usernameElement.appendChild(usernameText);

            let textElement = document.createElement('div');
            textElement.classList.add('flex-shrink-1', 'bg-light', 'rounded', 'py-2', 'px-3', 'mr-3');

            let messageText = document.createTextNode(message.message);
            textElement.appendChild(usernameElement);
            textElement.appendChild(messageText);
            messageElement.appendChild(textElement);
            messageArea.appendChild(messageElement);
            messageArea.scrollTop = messageArea.scrollHeight;
        }

        messageForm.addEventListener('submit', sendMessage, true)
        document.addEventListener("DOMContentLoaded", function () {
            connect();
        });
    </script>
</body>

</html>
<!DOCTYPE html>
<html>
<head>
    <#import "/spring.ftl" as spring/>
    <title><@spring.message code="chat.title"/></title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

    <style>
        .hidden {
            display: none;
        }
    </style>
</head>
<body>
    <h1>${model["Film"].title}</h1>
    <div id="chat-page" class="hidden">
        <div class="chat-container">
            <div class="connecting">
                <@spring.message code="chat.connecting"/>...
            </div>
            <ul id="messageArea">
                <#if model["Messages"]?has_content>
                    <#list model["Messages"] as msg>
                        <#if msg.userName == model["Name"]>
                            <#if .locale == "ru">
                                <#assign author="вы"/>
                            <#else>
                                <#assign author="you"/>
                            </#if>
                        <#else>
                            <#assign author="${msg.userName}"/>
                        </#if>
                        <li class="chat-message">
                            <span>
                                ${author}
                                <p>
                                    ${msg.message}
                                </p>
                            </span>
                        </li>
                    </#list>
                </#if>
            </ul>
            <form id="messageForm" name="messageForm">
                <div class="form-group">
                    <div class="input-group clearfix">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="text" id="message" placeholder="<@spring.message code="chat.placeholder"/>" autocomplete="off" class="form-control"/>
                        <button type="submit" class="primary"><@spring.message code="chat.send"/></button>
                    </div>
                </div>
            </form>
        </div>
    </div>


    <script>
        'use strict';

        var chatPage = document.querySelector('#chat-page');
        var messageForm = document.querySelector('#messageForm');
        var messageInput = document.querySelector('#message');
        var messageArea = document.querySelector('#messageArea');
        var connectingElement = document.querySelector('.connecting');

        var stompClient = null;

        function connect() {
            chatPage.classList.remove('hidden');
            var socket = new SockJS('/ws');
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
            var messageContent = messageInput.value.trim();
            if(messageContent && stompClient) {
                var chatMessage = {
                    userName: '${model["Name"]}',
                    message: messageInput.value,
                    filmId: '${model["Id"]}',
                };
                stompClient.send("/films/${model["Id"]}/chat/send", {}, JSON.stringify(chatMessage));
                messageInput.value = '';
            }
            event.preventDefault();
        }

        function onMessageReceived(payload) {
            var message = JSON.parse(payload.body);
            console.log(message);
            var messageElement = document.createElement('li');
            messageElement.classList.add('chat-message');
            var usernameElement = document.createElement('span');
            var usernameText;
            if ("${model["Name"]}" === message.userName) {
                usernameText = document.createTextNode("you");
            } else {
                usernameText = document.createTextNode(message.userName);
            }
            usernameElement.appendChild(usernameText);
            messageElement.appendChild(usernameElement);

            var textElement = document.createElement('p');
            var messageText = document.createTextNode(message.message);
            textElement.appendChild(messageText);
            messageElement.appendChild(textElement);
            messageArea.appendChild(messageElement);
            messageArea.scrollTop = messageArea.scrollHeight;
        }

        messageForm.addEventListener('submit', sendMessage, true)
        document.addEventListener("DOMContentLoaded", function() {
            connect();
        });
    </script>
</body>
</html>
package me.muszr.websocket;

import java.util.concurrent.CompletableFuture;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.websocket.WebSocketBroadcaster;
import io.micronaut.websocket.WebSocketSession;
import io.micronaut.websocket.annotation.OnClose;
import io.micronaut.websocket.annotation.OnMessage;
import io.micronaut.websocket.annotation.OnOpen;
import io.micronaut.websocket.annotation.ServerWebSocket;

@ServerWebSocket("/ws/case")
public class CaseWebSocket {

    private WebSocketBroadcaster broadcaster;
    private final Logger logger = LoggerFactory.getLogger(CaseWebSocket.class);

    public CaseWebSocket(WebSocketBroadcaster broadcaster) {
        this.broadcaster = broadcaster;
    }

    public <T> CompletableFuture<T> sendMessage(T msg) {
        return broadcaster.broadcastAsync(msg);
    }
    @OnMessage
    public void OnMessage(String message, WebSocketSession session) {
        logger.debug(message);
    }

    @OnOpen
    public void onOpen(WebSocketSession session) {
        logger.debug("New seesion opened");
    } 

    @OnClose
    public void onClose(WebSocketSession session) {
        logger.debug("Session closed");
    }
}

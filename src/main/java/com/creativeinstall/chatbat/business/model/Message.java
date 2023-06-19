package com.creativeinstall.chatbat.business.model;

public class Message {
    private int id;
    private int senderId;
    private int recipientId;
    private String messageText;
    private MessageStatus status;

    public Message(int id) {
    }

    public Message(int senderId, int recipientId, String messageText) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.messageText = messageText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }
}

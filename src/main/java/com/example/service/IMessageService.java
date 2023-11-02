package com.example.service;

import java.util.List;

import com.example.entity.Message;
import com.example.exception.InvalidMessageException;

public interface IMessageService {
    /**
     * Get all messages.
     * 
     * @return a possibly empty list of all messages
     */
    public List<Message> getAllMessages();

    /**
     * Get a message by id.
     * 
     * @param id the id of the message to get
     * @return the message requested, or <code>null</code> if a message with the given id does not exist
     */
    public Message getMessage(int id);

    /**
     * Post a new message.
     * 
     * @param message the message to post
     * @return the posted message
     * @throws InvalidMessageException if the message is empty, longer than 255 characters, or is posted by a non-existent user
     */
    public Message postMessage(Message message) throws InvalidMessageException;

    /**
     * Update the text of an existing message.
     * 
     * @param id the id of the message to update
     * @param message a message containing the new text
     * @throws InvalidMessageException if the message is empty or longer than 254 characters
     */
    public int updateMessage(int id, Message message) throws InvalidMessageException;

    /**
     * Delete a message.
     * 
     * @param id the id of the message to delete
     */
    public boolean deleteMessage(int id);

    /**
     * Get the messages posted by an account.
     * 
     * @param id the id of the account to get messages for
     * @return a possibly empty list of messages posted by the account requested
     */
    public List<Message> getAllMessagesForAccount(int id);
}

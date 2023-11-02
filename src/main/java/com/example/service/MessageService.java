package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.exception.InvalidMessageException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService implements IMessageService {
    private AccountRepository accountRepository;
    private MessageRepository messageRepository;

    public MessageService(AccountRepository accountRepository, MessageRepository messageRepository) {
        this.accountRepository = accountRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message getMessage(int id) {
        return messageRepository.findById(id);
    }

    @Override
    public Message postMessage(Message message) throws InvalidMessageException {
        if (message.getMessage_text().length() == 0 || message.getMessage_text().length() > 255 || !accountRepository.existsById(message.getPosted_by())) {
            throw new InvalidMessageException();
        }
        
        return messageRepository.save(message);
    }

    @Override
    public int updateMessage(int id, Message message) throws InvalidMessageException {
        if (message.getMessage_text().length() == 0 || message.getMessage_text().length() > 255) {
            throw new InvalidMessageException();
        }

        Message msg = messageRepository.findById(id);

        if (msg != null) {
            msg.setMessage_text(message.getMessage_text());

            messageRepository.save(msg);
        }

        return 1;
    }

    @Override
    public boolean deleteMessage(int id) {
        return messageRepository.deleteById(id) > 0;
    }

    @Override
    public List<Message> getAllMessagesForAccount(int id) {
        return messageRepository.findByPosted_by(id);
    }
}

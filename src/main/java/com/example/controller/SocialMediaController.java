package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.InvalidAccountException;
import com.example.exception.InvalidMessageException;
import com.example.exception.UnauthorizedException;
import com.example.service.IAccountService;
import com.example.service.IMessageService;

@RestController
public class SocialMediaController {
    private IAccountService accountService;
    private IMessageService messageService;
    
    public SocialMediaController(IAccountService accountService, IMessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public ResponseEntity<Account> postRegister(@RequestBody Account account) {
        try {
            return ResponseEntity.ok(accountService.register(account));
        } catch (InvalidAccountException e) {
            // CM: Spec says 400, test expects 409. Typo?
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Account> postLogin(@RequestBody Account account) {
        try {
            return ResponseEntity.ok(accountService.login(account));
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/messages")
    public List<Message> getMessages() {
        return messageService.getAllMessages();
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> postMessages(@RequestBody Message message) {
        try {
            return ResponseEntity.ok(messageService.postMessage(message));
        } catch (InvalidMessageException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/messages/{id}")
    public Message getMessagesId(@PathVariable int id) {
        return messageService.getMessage(id);
    }

    @PatchMapping("/messages/{id}")
    public ResponseEntity<Integer> patchMessagesId(@PathVariable int id, @RequestBody Message message) {
        try {
            Message record = messageService.getMessage(id);

            if (record == null) {
                return ResponseEntity.badRequest().build();
            }

            record.setMessage_text(message.getMessage_text());

            return ResponseEntity.ok(messageService.updateMessage(id, record));
        } catch (InvalidMessageException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/messages/{id}")
    public Integer deleteMessagesId(@PathVariable int id) {
        return messageService.deleteMessage(id) ? 1 : null;
    }

    @GetMapping("/accounts/{id}/messages")
    public List<Message> getAccountsIdMessages(@PathVariable int id) {
        return messageService.getAllMessagesForAccount(id);
    }
}

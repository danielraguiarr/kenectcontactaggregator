package com.kenectcontactaggregator.kenectcontactaggregator.service.validator;

import com.kenectcontactaggregator.kenectcontactaggregator.model.Contact;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContactValidator {

    public void validateContacts(List<Contact> contacts) {
        if (contacts == null || contacts.isEmpty()) {
            throw new IllegalArgumentException("No contacts found.");
        }
    }
}
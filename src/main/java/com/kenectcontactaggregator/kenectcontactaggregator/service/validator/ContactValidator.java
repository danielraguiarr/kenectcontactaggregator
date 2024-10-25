package com.kenectcontactaggregator.kenectcontactaggregator.service.validator;

import com.kenectcontactaggregator.kenectcontactaggregator.exception.ContactNotFoundException;
import com.kenectcontactaggregator.kenectcontactaggregator.exception.InvalidDataException;
import com.kenectcontactaggregator.kenectcontactaggregator.model.Contact;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContactValidator {

    public void validateContacts(List<Contact> contacts) {
        if (contacts == null || contacts.isEmpty()) {
            throw new ContactNotFoundException("No contacts found in the external API.");
        }
        for (Contact contact : contacts) {
            if (contact.id() <= 0) {
                throw new InvalidDataException("id", "Invalid contact ID.");
            }

            if (contact.name() == null) {
                throw new InvalidDataException("name", "Contact name is null.");
            }

            if (contact.email() == null) {
                throw new InvalidDataException("email", "Email is null.");
            }
        }
    }
}

package com.kenectcontactaggregator.kenectcontactaggregator.service.validator;

import com.kenectcontactaggregator.kenectcontactaggregator.exception.ContactNotFoundException;
import com.kenectcontactaggregator.kenectcontactaggregator.exception.InvalidDataException;
import com.kenectcontactaggregator.kenectcontactaggregator.model.Contact;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Component
public class ContactValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    );
    public void validateContacts(List<Contact> contacts) {
        if (contacts == null || contacts.isEmpty()) {
            throw new ContactNotFoundException("No contacts found in the external API.");
        }

        Set<Integer> uniqueIds = new HashSet<>();
        for (Contact contact : contacts) {
            if (contact.getId() <= 0) {
                throw new InvalidDataException("id", "Invalid contact ID.");
            }

            if (!uniqueIds.add(contact.getId())) {
                throw new InvalidDataException("id", "Duplicate contact ID detected.");
            }

            if (contact.getName() == null || contact.getName().trim().isEmpty()) {
                throw new InvalidDataException("name", "Contact name cannot be empty or null.");
            }

            if (contact.getEmail() == null || !EMAIL_PATTERN.matcher(contact.getEmail()).matches()) {
                throw new InvalidDataException("email", "Invalid email format.");
            }
        }
    }
}

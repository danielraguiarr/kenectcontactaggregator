package com.kenectcontactaggregator.kenectcontactaggregator.validator;

import com.kenectcontactaggregator.kenectcontactaggregator.exception.InvalidDataException;
import com.kenectcontactaggregator.kenectcontactaggregator.model.Contact;
import com.kenectcontactaggregator.kenectcontactaggregator.service.validator.ContactValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContactValidatorTest {
    private ContactValidator contactValidator;

    @BeforeEach
    void setUp() {
        contactValidator = new ContactValidator();
    }

    @Test
    void testValidateContacts_ValidData() {
        List<Contact> contacts = List.of(
                Contact.builder()
                        .id(1)
                        .name("Valid User")
                        .email("valid@example.com")
                        .source("KENECT_LABS")
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build()
        );

        contactValidator.validateContacts(contacts);
    }
    @Test
    void testValidateContacts_EmptyName() {
        List<Contact> contacts = List.of(
                 Contact.builder()
                        .id(1)
                        .name("")
                        .email("valid@example.com")
                        .source("KENECT_LABS")
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build()
        );

        assertThrows(InvalidDataException.class, () -> contactValidator.validateContacts(contacts));
    }
}

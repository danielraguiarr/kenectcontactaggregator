package com.kenectcontactaggregator.kenectcontactaggregator.validator;

import com.kenectcontactaggregator.kenectcontactaggregator.exception.InvalidDataException;
import com.kenectcontactaggregator.kenectcontactaggregator.model.Contact;
import com.kenectcontactaggregator.kenectcontactaggregator.service.validator.ContactValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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

    @Test
    void testValidateContacts_NameWithOnlySpaces() {
        List<Contact> contacts = List.of(
                Contact.builder()
                        .id(1)
                        .name("   ")
                        .email("valid@example.com")
                        .source("KENECT_LABS")
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build()
        );

        assertThrows(InvalidDataException.class, () -> contactValidator.validateContacts(contacts));
    }

    @Test
    void testValidateContacts_InvalidEmailFormat() {
        List<Contact> contacts = List.of(
                Contact.builder()
                        .id(1)
                        .name("Valid User")
                        .email("invalid-email")
                        .source("KENECT_LABS")
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build()
        );

        assertThrows(InvalidDataException.class, () -> contactValidator.validateContacts(contacts));
    }

    @Test
    void testValidateContacts_ValidSpecialCharacterEmail() {
        List<Contact> contacts = List.of(
                Contact.builder()
                        .id(1)
                        .name("Special Char User")
                        .email("special+char@example.com")
                        .source("KENECT_LABS")
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build()
        );

        assertDoesNotThrow(() -> contactValidator.validateContacts(contacts));
    }

    @Test
    void testValidateContacts_DuplicateIds() {
        List<Contact> contacts = List.of(
                Contact.builder()
                        .id(1)
                        .name("User One")
                        .email("userone@example.com")
                        .source("KENECT_LABS")
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build(),
                Contact.builder()
                        .id(1)
                        .name("User Two")
                        .email("usertwo@example.com")
                        .source("KENECT_LABS")
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build()
        );

        assertThrows(InvalidDataException.class, () -> contactValidator.validateContacts(contacts), "Duplicate ID detected.");
    }
}

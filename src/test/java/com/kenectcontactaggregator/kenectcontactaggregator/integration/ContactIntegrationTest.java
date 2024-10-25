package com.kenectcontactaggregator.kenectcontactaggregator.integration;

import com.kenectcontactaggregator.kenectcontactaggregator.model.Contact;
import com.kenectcontactaggregator.kenectcontactaggregator.service.impl.ContactServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class ContactIntegrationTest {

    @Autowired
    private ContactServiceImpl contactService;

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    void testGetAllContacts_Integration() {
        List<Contact> contacts = contactService.getAllContacts();

        assertFalse(contacts.isEmpty(), "No contacts found - check API integration.");
    }
}

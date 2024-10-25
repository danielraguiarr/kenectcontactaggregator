package com.kenectcontactaggregator.kenectcontactaggregator.service;

import com.kenectcontactaggregator.kenectcontactaggregator.exception.ContactNotFoundException;
import com.kenectcontactaggregator.kenectcontactaggregator.model.Contact;
import com.kenectcontactaggregator.kenectcontactaggregator.service.helper.ContactHelper;
import com.kenectcontactaggregator.kenectcontactaggregator.service.impl.ContactServiceImpl;
import com.kenectcontactaggregator.kenectcontactaggregator.service.validator.ContactValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ContactServiceImplTest {

    @InjectMocks
    private ContactServiceImpl contactService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ContactHelper contactHelper;

    @Mock
    private ContactValidator contactValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllContacts_Success() {
        List<Contact> mockContacts = List.of(
                Contact.builder()
                        .id(1)
                        .name("John Doe")
                        .email("john@example.com")
                        .source("KENECT_LABS")
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build()
        );

        when(contactHelper.fetchContacts(any(RestTemplate.class))).thenReturn(mockContacts);

        List<Contact> contacts = contactService.getAllContacts();

        verify(contactHelper, times(1)).fetchContacts(any(RestTemplate.class));
        verify(contactValidator, times(1)).validateContacts(mockContacts);

        assertEquals(1, contacts.size());
        assertEquals("John Doe", contacts.getFirst().name());
    }

    @Test
    void testGetAllContacts_NoContactsFound() {
        List<Contact> emptyContacts = List.of();
        when(contactHelper.fetchContacts(any(RestTemplate.class))).thenReturn(emptyContacts);

        assertThrows(ContactNotFoundException.class, () -> contactService.getAllContacts());

        verify(contactValidator, times(1)).validateContacts(emptyContacts);
    }
}

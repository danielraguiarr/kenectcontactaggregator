package com.kenectcontactaggregator.kenectcontactaggregator.service.impl;

import com.kenectcontactaggregator.kenectcontactaggregator.exception.ContactNotFoundException;
import com.kenectcontactaggregator.kenectcontactaggregator.model.Contact;
import com.kenectcontactaggregator.kenectcontactaggregator.service.ContactService;
import com.kenectcontactaggregator.kenectcontactaggregator.service.helper.ContactHelper;
import com.kenectcontactaggregator.kenectcontactaggregator.service.validator.ContactValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private ContactHelper contactHelper;

    @Autowired
    private ContactValidator contactValidator;

    @Override
    public List<Contact> getAllContacts() {
        List<Contact> contacts = contactHelper.fetchContacts(restTemplate);
        contactValidator.validateContacts(contacts);
        if (contacts.isEmpty()) {
            throw new ContactNotFoundException("No contacts found");
        }
        return contacts;
    }
}

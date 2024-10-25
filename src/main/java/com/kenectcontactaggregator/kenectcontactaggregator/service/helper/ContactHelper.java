package com.kenectcontactaggregator.kenectcontactaggregator.service.helper;

import com.kenectcontactaggregator.kenectcontactaggregator.model.Contact;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContactHelper {

    @Value("${api.kenect.url}")
    private String apiUrl;

    @Value("${api.kenect.token}")
    private String apiToken;

    public List<Contact> fetchContacts(RestTemplate restTemplate) {
        List<Contact> allContacts = new ArrayList<>();
        int currentPage = 1;
        boolean hasMorePages = true;

        while (hasMorePages) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiToken);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            var response = restTemplate.exchange(
                    apiUrl + "?page=" + currentPage,
                    HttpMethod.GET,
                    entity,
                    Contact[].class
            );

            if (response.getBody() != null) {
                for (Contact contact : response.getBody()) {
                    Contact builtContact = Contact.builder()
                            .id(contact.id())
                            .name(contact.name())
                            .email(contact.email())
                            .source(contact.source())
                            .createdAt(contact.createdAt())
                            .updatedAt(contact.updatedAt())
                            .build();
                    allContacts.add(builtContact);
                }
                currentPage++;
                hasMorePages = checkNextPage(response.getHeaders());
            } else {
                hasMorePages = false;
            }
        }
        return allContacts;
    }

    private boolean checkNextPage(HttpHeaders headers) {
        String linkHeader = headers.getFirst("Link");
        return linkHeader != null && linkHeader.contains("rel=\"next\"");
    }
}
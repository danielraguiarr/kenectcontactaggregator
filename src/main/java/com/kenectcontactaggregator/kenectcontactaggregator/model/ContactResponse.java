package com.kenectcontactaggregator.kenectcontactaggregator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContactResponse {
    @JsonProperty("contacts")
    private List<Contact> contacts;
}
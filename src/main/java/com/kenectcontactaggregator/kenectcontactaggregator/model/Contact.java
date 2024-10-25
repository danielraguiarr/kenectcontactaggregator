package com.kenectcontactaggregator.kenectcontactaggregator.model;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Contact(
        int id,
        String name,
        String email,
        String source,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
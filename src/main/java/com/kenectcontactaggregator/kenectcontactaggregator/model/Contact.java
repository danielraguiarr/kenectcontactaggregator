package com.kenectcontactaggregator.kenectcontactaggregator.model;

import java.time.LocalDateTime;

public record Contact(
        int id,
        String name,
        String email,
        String source,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
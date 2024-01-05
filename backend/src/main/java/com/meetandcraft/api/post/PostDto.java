package com.meetandcraft.api.post;

import lombok.Data;

import java.util.UUID;

@Data
public class PostDto {
    private UUID id;
    private String title;
    private String content;
}

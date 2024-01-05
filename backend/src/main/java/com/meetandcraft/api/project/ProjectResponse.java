package com.meetandcraft.api.project;

import lombok.Data;

import java.util.List;

@Data
public class ProjectResponse {
    private List<ProjectDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}


/*
@Data
public class PokemonResponse {
    private List<PokemonDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
 */
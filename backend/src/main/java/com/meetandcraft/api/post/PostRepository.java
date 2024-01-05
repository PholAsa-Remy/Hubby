package com.meetandcraft.api.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface PostRepository extends JpaRepository<Post, UUID> {
}

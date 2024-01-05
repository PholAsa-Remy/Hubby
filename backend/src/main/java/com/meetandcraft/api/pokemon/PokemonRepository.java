package com.meetandcraft.api.pokemon;

import com.meetandcraft.api.pokemon.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}

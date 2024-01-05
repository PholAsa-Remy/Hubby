package com.meetandcraft.api.pokemon;

import com.meetandcraft.api.pokemon.PokemonDto;
import com.meetandcraft.api.pokemon.PokemonResponse;

public interface PokemonService {
    PokemonDto createPokemon (PokemonDto pokemonDto);
    PokemonResponse getAllPokemons (int pageNo, int pageSize);
    PokemonDto getPokemonById(int id);
    PokemonDto updatePokemonById(PokemonDto pokemonDto, int id);
    void deletePokemonById(int id);
}

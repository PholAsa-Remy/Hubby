package com.meetandcraft.api.pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class PokemonController {

    private PokemonService pokemonService;

    @Autowired
    public PokemonController (PokemonService pokemonService){
        this.pokemonService = pokemonService;
    }

    @GetMapping("pokemon")
    public ResponseEntity<PokemonResponse> getPokemons (
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        return new ResponseEntity<>(pokemonService.getAllPokemons(pageNo,pageSize),HttpStatus.OK);
    }

    @GetMapping("pokemon/{id}")
    public ResponseEntity<PokemonDto> pokemonDetail (@PathVariable int id) {
        return new ResponseEntity<>(pokemonService.getPokemonById(id),HttpStatus.OK);
    }

    @PostMapping("pokemon")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto> createPokemon (@RequestBody PokemonDto pokemonDto){
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDto), HttpStatus.CREATED);
    }

    @PutMapping("pokemon/{id}")
    public ResponseEntity<PokemonDto> updatePokemon (@RequestBody PokemonDto pokemonDto, @PathVariable("id") int pokemonId){
        PokemonDto response = pokemonService.updatePokemonById(pokemonDto,pokemonId);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @DeleteMapping("pokemon/{id}")
    public ResponseEntity<String> deletePokemon (@PathVariable("id") int pokemonId){
        pokemonService.deletePokemonById(pokemonId);
        return new ResponseEntity<>("Pokemon deleted successfully", HttpStatus.OK);
    }
}

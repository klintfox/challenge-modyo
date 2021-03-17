package com.pokedex.service;

import com.pokedex.response.PokemonAdvanceResponse;
import com.pokedex.response.PokemonBasicResponse;

public interface PokemonService {

	PokemonBasicResponse getPokemonBasicInformation(String name) throws Exception;

	PokemonAdvanceResponse getPokemonAdvanceInformation(String name) throws Exception;

}

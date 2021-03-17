package com.pokedex.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokedex.api.PokedexClient;
import com.pokedex.response.PokemonAdvanceResponse;
import com.pokedex.response.PokemonBasicResponse;
import com.pokedex.response.PokemonEvolutionsResponse;
import com.pokedex.service.PokemonService;

@Service
public class PokemonServiceImpl implements PokemonService {

	@Autowired
	private PokedexClient client;

	@Override
	public PokemonBasicResponse getPokemonBasicInformation(String name) throws Exception {
		return client.getPokemonBasicInformation(name);
	}

	@Override
	public PokemonAdvanceResponse getPokemonAdvanceInformation(String name) throws Exception {
		PokemonAdvanceResponse response = new PokemonAdvanceResponse();
		PokemonBasicResponse basic = new PokemonBasicResponse();
		basic = client.getPokemonBasicInformation(name);

		PokemonEvolutionsResponse evolutions = new PokemonEvolutionsResponse();
		evolutions = client.getPokemonEvolutions(basic.getId());

		response.setBasic(basic);
		response.setDescription(client.getPokemonDescription(basic.getId()));
		response.setChain(evolutions.getChain());

		return response;
	}

}
package com.pokedex.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokedex.api.PokedexClient;
import com.pokedex.model.Evolves;
import com.pokedex.response.PokemonAdvanceResponse;
import com.pokedex.response.PokemonBasicResponse;
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
		List<Evolves> lstEvolves = client.getPokemonEvolutions(basic.getId());
		//Setting Response
		response.setBasic(basic);
		response.setDescription(client.getPokemonDescription(basic.getId()));
		response.setEvolves(lstEvolves);

		return response;
	}

}
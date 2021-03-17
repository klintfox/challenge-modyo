package com.pokedex.response;

import com.google.gson.JsonObject;

public class PokemonEvolutionsResponse {

	JsonObject chain;

	public PokemonEvolutionsResponse() {}
	
	public JsonObject getChain() {
		return chain;
	}

	public void setChain(JsonObject chain) {
		this.chain = chain;
	}

	@Override
	public String toString() {
		return "PokemonEvolutionsResponse [chain=" + chain + "]";
	}

	
	
	
}

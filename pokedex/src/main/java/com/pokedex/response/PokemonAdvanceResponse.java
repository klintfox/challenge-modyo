package com.pokedex.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.JsonObject;

public class PokemonAdvanceResponse {

	@JsonProperty(value = "basic")
	PokemonBasicResponse basic;

	String description;

	JsonObject chain;

	public PokemonBasicResponse getBasic() {
		return basic;
	}

	public void setBasic(PokemonBasicResponse basic) {
		this.basic = basic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public JsonObject getChain() {
		return chain;
	}

	public void setChain(JsonObject chain) {
		this.chain = chain;
	}

}

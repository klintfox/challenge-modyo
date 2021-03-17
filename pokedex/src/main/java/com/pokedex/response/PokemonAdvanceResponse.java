package com.pokedex.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pokedex.model.Evolves;

public class PokemonAdvanceResponse {

	@JsonProperty(value = "basic")
	PokemonBasicResponse basic;

	String description;

	List<Evolves> evolves;

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

	public List<Evolves> getEvolves() {
		return evolves;
	}

	public void setEvolves(List<Evolves> evolves) {
		this.evolves = evolves;
	}

}

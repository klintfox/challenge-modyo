package com.pokedex.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pokedex.model.PokemonBasicInformation;

public class PokemonBasicResponse {

	String id;
	String photo;

	@JsonProperty(value = "basic_information")
	PokemonBasicInformation basicInformation;

	public PokemonBasicResponse() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public PokemonBasicInformation getBasicInformation() {
		return basicInformation;
	}

	public void setBasicInformation(PokemonBasicInformation basicInformation) {
		this.basicInformation = basicInformation;
	}

}
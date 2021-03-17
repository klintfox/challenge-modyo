package com.pokedex.model;

import java.util.List;

public class PokemonBasicInformation {

	List<Type> type;
	String weight;
	List<Abilities> abilities;

	public PokemonBasicInformation() {
	}

	public List<Type> getType() {
		return type;
	}

	public void setType(List<Type> type) {
		this.type = type;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public List<Abilities> getAbilities() {
		return abilities;
	}

	public void setAbilities(List<Abilities> abilities) {
		this.abilities = abilities;
	}

}

package com.pokedex.model;

import java.util.List;

public class Evolves {

	List<EvolutionDetails> evolution_details;
	String[] evolves_to;
	boolean is_baby;
	Species species;

	public Evolves() {
	}

	public List<EvolutionDetails> getEvolution_details() {
		return evolution_details;
	}

	public void setEvolution_details(List<EvolutionDetails> evolution_details) {
		this.evolution_details = evolution_details;
	}

	public String[] getEvolves_to() {
		return evolves_to;
	}

	public void setEvolves_to(String[] evolves_to) {
		this.evolves_to = evolves_to;
	}

	public boolean isIs_baby() {
		return is_baby;
	}

	public void setIs_baby(boolean is_baby) {
		this.is_baby = is_baby;
	}

	public Species getSpecies() {
		return species;
	}

	public void setSpecies(Species species) {
		this.species = species;
	}

}

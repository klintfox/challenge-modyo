package com.pokedex.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pokedex.model.Abilities;
import com.pokedex.model.Description;
import com.pokedex.model.Evolves;
import com.pokedex.model.PokemonBasicInformation;
import com.pokedex.model.Type;
import com.pokedex.model.Types;
import com.pokedex.response.PokemonBasicResponse;

import reactor.core.publisher.Mono;

@Component
public class PokedexClient {

	@Autowired
	private WebClient webClient;

	@SuppressWarnings("deprecation")
	public PokemonBasicResponse getPokemonBasicInformation(String name) throws Exception {
		PokemonBasicResponse response = new PokemonBasicResponse();
		PokemonBasicInformation basicInformation = new PokemonBasicInformation();
		Mono<String> responseFromApi = webClient.get().uri("https://pokeapi.co/api/v2/pokemon/" + name).retrieve()
				.bodyToMono(String.class);
		JsonObject jsonObject = new JsonParser().parse(responseFromApi.block()).getAsJsonObject();
		JsonArray jsonAbilities = (JsonArray) jsonObject.get("abilities");
		JsonArray jsonTypes = (JsonArray) jsonObject.get("types");
		JsonElement jsonPhoto = jsonObject.get("sprites").getAsJsonObject().get("front_default");
		JsonElement jsonWeight = jsonObject.get("weight");
		JsonElement jsonId = jsonObject.get("id");
		// Abilities
		ObjectMapper mapperAbilities = new ObjectMapper();
		List<Abilities> lstAbilities = Arrays
				.asList(mapperAbilities.readValue(jsonAbilities.toString(), Abilities[].class));
		// Types
		ObjectMapper mapperTypes = new ObjectMapper();
		List<Types> lstTypes = Arrays.asList(mapperTypes.readValue(jsonTypes.toString(), Types[].class));
		List<Type> lstType = new ArrayList<Type>();
		Type type;
		for (int i = 0; i < lstTypes.size(); i++) {
			type = new Type();
			type.setName(lstTypes.get(i).getType().getName());
			type.setUrl(lstTypes.get(i).getType().getUrl());
			lstType.add(type);
		}
		basicInformation.setWeight(jsonWeight.toString());
		basicInformation.setType(lstType);
		basicInformation.setAbilities(lstAbilities);
		// Setting response
		response.setId(jsonId.toString());
		response.setPhoto(jsonPhoto.toString());
		response.setBasicInformation(basicInformation);
		return response;
	}

	@SuppressWarnings("deprecation")
	public List<Evolves>  getPokemonEvolutions(String id) throws Exception{	
		List<Evolves> lstEvolves = new ArrayList<>();
		Mono<String> responseFromApi = webClient.get().uri("https://pokeapi.co/api/v2/evolution-chain/" + id).retrieve()
				.bodyToMono(String.class);
		JsonObject jsonObject = new JsonParser().parse(responseFromApi.block()).getAsJsonObject();
		JsonArray jsonChain = jsonObject.get("chain").getAsJsonObject().getAsJsonArray("evolves_to");		
		ObjectMapper mapperEvolves = new ObjectMapper();
		lstEvolves = Arrays
				.asList(mapperEvolves.readValue(jsonChain.toString(), Evolves[].class));
		return lstEvolves;
	}

	@SuppressWarnings("deprecation")
	public String getPokemonDescription(String id) throws Exception {
		String description = "";
		WebClient webClient = WebClient.create("https://pokeapi.co/api/v2/characteristic/");
		Mono<String> responseFromApi = webClient.get().uri(id).retrieve().bodyToMono(String.class);
		JsonObject jsonObject = new JsonParser().parse(responseFromApi.block()).getAsJsonObject();
		JsonArray jsonAbilities = (JsonArray) jsonObject.get("descriptions");

		System.out.println(jsonAbilities.toString());
		ObjectMapper mapperDescriptions = new ObjectMapper();
		List<Description> lstDescriptions = Arrays
				.asList(mapperDescriptions.readValue(jsonAbilities.toString(), Description[].class));
		for (int i = 0; i < lstDescriptions.size(); i++) {
			if (i == 1) {
				description = lstDescriptions.get(i).getDescription();
			}
		}
		return description;
	}

}
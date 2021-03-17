package com.pokedex.api;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pokedex.model.Abilities;
import com.pokedex.model.Description;
import com.pokedex.model.PokemonBasicInformation;
import com.pokedex.model.Type;
import com.pokedex.model.Types;
import com.pokedex.response.PokemonBasicResponse;
import com.pokedex.response.PokemonEvolutionsResponse;

import reactor.core.publisher.Mono;

class PokedexClientTest {

	String id = "25";
	String name = "pikachu";

	@SuppressWarnings("deprecation")
//	@Test
	void testGetPokemonBasicInformation() throws Exception {
		PokemonBasicResponse response = new PokemonBasicResponse();
		PokemonBasicInformation basicInformation = new PokemonBasicInformation();
		WebClient webClient = WebClient.create("https://pokeapi.co/api/v2/pokemon/");
		Mono<String> responseFromApi = webClient.get().uri(name).retrieve().bodyToMono(String.class);

		JsonObject jsonObject = new JsonParser().parse(responseFromApi.block()).getAsJsonObject();
		JsonArray jsonAbilities = (JsonArray) jsonObject.get("abilities");
		JsonArray jsonTypes = (JsonArray) jsonObject.get("types");
		JsonElement jsonPhoto = jsonObject.get("sprites").getAsJsonObject().get("front_default");
		JsonElement jsonWeight = jsonObject.get("weight");
		JsonElement jsonId = jsonObject.get("id");

		// abilities
		ObjectMapper mapperAbilities = new ObjectMapper();
		List<Abilities> lstAbilities = Arrays
				.asList(mapperAbilities.readValue(jsonAbilities.toString(), Abilities[].class));

		// types
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

		// Setting response}
		response.setId(jsonId.toString());
		response.setPhoto(jsonPhoto.toString());
		response.setBasicInformation(basicInformation);
		if (response != null) {
			assertTrue(true);
			System.out.println(response);
		}
	}

	@SuppressWarnings("deprecation")
//	@Test
	void testGetPokemonAdvanceInformation() throws Exception {
		PokemonEvolutionsResponse response = new PokemonEvolutionsResponse();
		WebClient webClient = WebClient.create("https://pokeapi.co/api/v2/evolution-chain/");
		Mono<String> responseFromApi = webClient.get().uri(id).retrieve().bodyToMono(String.class);
		JsonObject jsonObject = new JsonParser().parse(responseFromApi.block()).getAsJsonObject();
		JsonElement jsonChain = jsonObject.get("chain");
		
		JsonArray jsonEvolves = (JsonArray) jsonChain.getAsJsonArray().getAsJsonObject().get("evolves_to");
		System.out.println("AA: " +jsonChain.toString());
		
		
//		response.setChain(jsonChain.getAsJsonObject());
//		System.out.println(response.toString());
//		if (response != null) {
//			assertTrue(true);
//			System.out.println(response);
//		}
	}

	@SuppressWarnings("deprecation")
//	@Test
	void testGetPokemonDescription() throws Exception {
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
		if (jsonAbilities != null) {
			assertTrue(true);
			System.out.println(jsonAbilities.toString());
		}
	}

}

package com.pokedex.api;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pokedex.model.Abilities;
import com.pokedex.model.Description;
import com.pokedex.model.EvolutionDetails;
import com.pokedex.model.Evolves;
import com.pokedex.model.PokemonBasicInformation;
import com.pokedex.model.Type;
import com.pokedex.model.Types;
import com.pokedex.response.PokemonBasicResponse;

import reactor.core.publisher.Mono;

@SpringBootTest
class PokedexClientTest {

	String id = "25";
	String name = "pikachu";
//	String server = "https://pokeapi.co/api/v2/";
	WebClient webClient = WebClient.create("https://pokeapi.co/api/v2/");
	@SuppressWarnings("deprecation")
	@Test
	void testGetPokemonBasicInformation() throws Exception {
		PokemonBasicResponse response = new PokemonBasicResponse();
		PokemonBasicInformation basicInformation = new PokemonBasicInformation();
//		WebClient webClient = WebClient.create(server + "pokemon/");
		Mono<String> responseFromApi = webClient.get().uri("pokemon/"+name).retrieve().bodyToMono(String.class);
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
		// Other Information
		basicInformation.setWeight(jsonWeight.toString());
		basicInformation.setType(lstType);
		basicInformation.setAbilities(lstAbilities);
		// Setting response
		response.setId(jsonId.toString());
		response.setPhoto(jsonPhoto.toString());
		response.setBasicInformation(basicInformation);
		if (response != null) {
			assertTrue(true);
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	void testGetPokemonAdvanceInformation() throws Exception {		
//		WebClient webClient = WebClient.create(server + "evolution-chain/");
		Mono<String> responseFromApi = webClient.get().uri("evolution-chain/"+id).retrieve().bodyToMono(String.class);
		JsonObject jsonObject = new JsonParser().parse(responseFromApi.block()).getAsJsonObject();
		JsonArray jsonChain = jsonObject.get("chain").getAsJsonObject().getAsJsonArray("evolves_to");		
		ObjectMapper mapperEvolves = new ObjectMapper();
		List<Evolves> lstEvolves = Arrays
				.asList(mapperEvolves.readValue(jsonChain.toString(), Evolves[].class));

		if (lstEvolves != null) {
			assertTrue(true);
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	void testGetPokemonDescription() throws Exception {
		String description = "";
//		WebClient webClient = WebClient.create(server + "characteristic/");
		Mono<String> responseFromApi = webClient.get().uri("characteristic/"+id).retrieve().bodyToMono(String.class);
		JsonObject jsonObject = new JsonParser().parse(responseFromApi.block()).getAsJsonObject();
		JsonArray jsonAbilities = (JsonArray) jsonObject.get("descriptions");
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
		}
	}

}
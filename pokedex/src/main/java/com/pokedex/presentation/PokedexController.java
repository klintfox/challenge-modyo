package com.pokedex.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pokedex.exception.InternalServerError;
import com.pokedex.response.PokemonAdvanceResponse;
import com.pokedex.response.PokemonBasicResponse;
import com.pokedex.service.PokemonService;

import io.swagger.annotations.ApiOperation;

@RestController
public class PokedexController {

	@Autowired
	private PokemonService service;

	@ApiOperation(value = "this endpoint show the baisc information about one pokemon")
	@GetMapping("v1/pokemon/basic-information/{name}")
	public PokemonBasicResponse pokemonbasicInformation(@PathVariable String name) throws InternalServerError {
		try {
			System.out.println(name);
			return service.getPokemonBasicInformation(name);
		} catch (Exception e) {
			throw new InternalServerError(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), e.getMessage());
		}
	}

	@ApiOperation(value = "this endpoint show more detail information about one pokemon")
	@GetMapping("v1/pokemon/advance-information/{name}")
	public PokemonAdvanceResponse pokemonaditionalInformation(@PathVariable String name) throws InternalServerError {
		try {
			return service.getPokemonAdvanceInformation(name);
		} catch (Exception e) {
			throw new InternalServerError(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), e.getMessage());
		}
	}
}

package com.devsuperior.dsmovie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.entities.Movie;
import com.devsuperior.dsmovie.repositories.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	public MovieRepository repository;
	
	// somente leitura fica mais eficiente para o banco de dados
	@Transactional(readOnly = true) 
	public Page<MovieDTO> findAll(Pageable pageable) {
		Page<Movie> result = repository.findAll(pageable);
		Page<MovieDTO> page = result.map(x -> new MovieDTO(x));
		return page;
	}
	
	@Transactional(readOnly = true) 
	public MovieDTO findById(Long id) {
		// .findById() retorna um objeto Optional(obj especial do Java), 
		// pra acessar o objeto Movie que está dentro o Optional tem que usar o .get()
		// para refinar mais, acrescentar uma verificacao para ver se o id existe
		Movie result = repository.findById(id).get(); 
		MovieDTO dto = new MovieDTO(result);
		return dto;
	}
	

}

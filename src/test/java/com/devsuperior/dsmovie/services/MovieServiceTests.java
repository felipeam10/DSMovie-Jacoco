package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.entities.MovieEntity;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import com.devsuperior.dsmovie.tests.MovieFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class MovieServiceTests {
	
	@InjectMocks
	private MovieService service;
	@Mock
	private MovieRepository repository;
	private MovieDTO movieDTO;
	private MovieEntity movie;
	private String title;
	private Long existingId;

	@BeforeEach
	public void setup() throws Exception {
		existingId = 1L;
		title = "MIB";
		movieDTO = MovieFactory.createMovieDTO();
		movie = MovieFactory.createMovieEntity();
		PageImpl<MovieEntity> page = new PageImpl<>(List.of(movie));

		Mockito.when(repository.searchByTitle(any(), any())).thenReturn(page);
		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(movie));
	}
	
	@Test
	public void findAllShouldReturnPagedMovieDTO() {
		Pageable pageable = PageRequest.of(0, 12);
		Page<MovieDTO> result = service.findAll(title, pageable);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getSize(), 1);
	}
	
	@Test
	public void findByIdShouldReturnMovieDTOWhenIdExists() {
		MovieDTO result = service.findById(movie.getId());
		Assertions.assertNotNull(result);
		Assertions.assertEquals(movie.getId(), result.getId());
		Assertions.assertEquals(movie.getTitle(), result.getTitle());
		Assertions.assertEquals(movie.getScore(), result.getScore());
		Assertions.assertEquals(movie.getCount(), result.getCount());
		Assertions.assertEquals(movie.getImage(), result.getImage());
	}
	
	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
	}
	
	@Test
	public void insertShouldReturnMovieDTO() {
	}
	
	@Test
	public void updateShouldReturnMovieDTOWhenIdExists() {
	}
	
	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
	}
	
	@Test
	public void deleteShouldDoNothingWhenIdExists() {
	}
	
	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
	}
	
	@Test
	public void deleteShouldThrowDatabaseExceptionWhenDependentId() {
	}
}

package com.udacity.bootstrap.service;

import com.udacity.bootstrap.entity.Dog;

import java.util.List;

public interface DogService {
    List<Dog> getDogs();
    List<String> getBreeds();
    String getDogBreedById(Long id);
    List<String> getNames();
}

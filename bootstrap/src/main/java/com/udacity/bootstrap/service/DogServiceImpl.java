package com.udacity.bootstrap.service;

import com.udacity.bootstrap.entity.Dog;
import com.udacity.bootstrap.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogServiceImpl implements DogService{
    @Autowired
    private DogRepository dogRepository;

    public List<Dog> getDogs(){
        return (List<Dog>) dogRepository.findAll();
    }

    public List<String> getNames(){
        return dogRepository.findAllName();
    }

    public List<String> getBreeds(){
        return dogRepository.findAllBreed();
    }

    public String getDogBreedById(Long id){
        return dogRepository.findBreedById(id);
    }

    //different method to handle the error (id doesn't exist) within the service class
    public String getDogBreedByIdOrThrowError(Long id){
        Optional<String> optionalBreed = Optional.ofNullable(dogRepository.findBreedById(id));
        String breed = optionalBreed.orElseThrow(DogNotFoundException::new);
        return breed;
    }




}

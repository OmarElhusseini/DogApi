package com.udacity.bootstrap.web;

import com.udacity.bootstrap.entity.Dog;
import com.udacity.bootstrap.service.DogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DogController {
    private DogServiceImpl dogService;

    @Autowired
    public void setDogService(DogServiceImpl dogService) {
        this.dogService = dogService;
    }

    @GetMapping("/dog")
    public ResponseEntity<List<Dog>> getAllDogs(){
        List<Dog> dogList = dogService.getDogs();
        return new ResponseEntity<List<Dog>>(dogList, HttpStatus.OK);
    }

    @GetMapping("/dogbreed")
    public ResponseEntity<List<String>> getAllBreeds(){
        List<String> dogBreedList = dogService.getBreeds();
        return new ResponseEntity<List<String>>(dogBreedList, HttpStatus.OK);
    }

    /*As the name suggests, @RequestParam is used to get the request parameters from URL,
    also known as query parameters, while @PathVariable extracts values from URI.

    For example, if the incoming HTTP request to retrieve a book on topic "Java"
    is http://localhost:8080/shop/order/1001/receipts?date=12-05-2017,
    then you can use the @RequestParam annotation to retrieve the query parameter date and
    you can use @PathVariable to extract the orderId i.e. "1001" as shown below:

    @RequestMapping(value="/order/{orderId}/receipts", method = RequestMethod.GET)
    public List listUsersInvoices( @PathVariable("orderId") int order,
                                   @RequestParam(value = "date", required = false) Date dateOrNull) {
...
    }*/

    //three different ways to retrieve data using id written in path and handle the error if the id doesn't exist
    @GetMapping("/dog/breed")
    public ResponseEntity<String> getBreedById(@RequestParam(value = "id", required = false) Long id){
        String dogBreed = dogService.getDogBreedById(id);
        if (dogBreed!=null){
            return new ResponseEntity<String>(dogBreed, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/dog/breed/{id}")
    public ResponseEntity<String> getDogBreedById(@PathVariable("id") Long id){
        String dogBreed = dogService.getDogBreedById(id);
        if (dogBreed!=null){
            return new ResponseEntity<String>(dogBreed, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //this one doesn't work as expected???!!!
    @GetMapping("/{id}/dogbreed")
    public ResponseEntity<String> gerDogBreedByIdOrThrowError(@PathVariable("id") Long id){
        String dogBreed = dogService.getDogBreedByIdOrThrowError(id);
        return new ResponseEntity<String>(dogBreed, HttpStatus.OK);
    }
}

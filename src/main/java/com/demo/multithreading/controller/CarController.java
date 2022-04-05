package com.demo.multithreading.controller;

import com.demo.multithreading.entity.Car;
import com.demo.multithreading.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 *
 */
@RestController
@RequestMapping(value = "/api/car", produces = "application/json")
public class CarController {


    @Autowired
    private CarService carService;

    /**
     * If multiple files are passed, 2 threads will be spawned to cater the request.
     * eg. 1 file  - 1 Thread, 1
     * 2 files - 2 Threads, 1-2
     * 3 files - 2 Threads, 1-2-1
     *
     * @param files -> Multipart file
     * @return ResponseEntity
     */
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseBody
    ResponseEntity<?> uploadFile(
            @RequestParam(value = "file") MultipartFile[] files) {
        try {
            for (final MultipartFile file : files) {
                if (file.isEmpty())
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                carService.saveCars(file);
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    /**
     * Spawns 3 threads from the pool size of 2 and waits until result is completed/joined to display output.
     *
     * @return List of Car
     */
    @RequestMapping(method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    ResponseEntity<?> getAllCars() {
        try {
            CompletableFuture<List<Car>> cars1 = carService.getAllCars();
            CompletableFuture<List<Car>> cars2 = carService.getAllCars();
            CompletableFuture<List<Car>> cars3 = carService.getAllCars();

            CompletableFuture.allOf(cars1, cars2, cars3).join();

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

package com.demo.multithreading.service;

import com.demo.multithreading.entity.Car;
import com.demo.multithreading.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CarService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);

    @Autowired
    private CarRepository carRepository;

    /**
     * @param file Accepts a file
     * @return CompletableFuture of Cars saved to database
     * @throws Exception if parsing fails
     */
    @Async
    public CompletableFuture<List<Car>> saveCars(final MultipartFile file) throws Exception {
        final long start = System.currentTimeMillis();

        List<Car> cars = parseCSVFile(file);

        LOGGER.info("Saving a list of cars of size {} records", cars.size());

        cars = carRepository.saveAll(cars);

        LOGGER.info("Elapsed time: {}", (System.currentTimeMillis() - start));
        return CompletableFuture.completedFuture(cars);
    }

    private List<Car> parseCSVFile(final MultipartFile file) throws Exception {
        final List<Car> cars = new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    final String[] data = line.split(",");
                    final Car car = new Car();
                    car.setId(data[0]);
                    car.setMake(data[1]);
                    car.setModel(data[2]);
                    car.setYear(data[3]);
                    cars.add(car);
                }
                LOGGER.info("Parsed successfully  {}", file.getOriginalFilename());
                return cars;
            }
        } catch (final IOException e) {
            LOGGER.error("Failed to parse CSV file {}", file.getOriginalFilename());
            throw new Exception("Failed to parse CSV file {}", e);
        }
    }

    /**
     * Async method to find all the Car data from database
     *
     * @return CompletableFuture of Car
     */
    @Async
    public CompletableFuture<List<Car>> getAllCars() {
        final List<Car> cars = carRepository.findAll();
        LOGGER.info("Request to get a list of total cars " + cars.size());
        return CompletableFuture.completedFuture(cars);
    }
}

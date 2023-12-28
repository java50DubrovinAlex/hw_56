package telran.cars.service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import telran.cars.dto.*;
import telran.cars.exceptions.NotFoundException;
import telran.cars.service.model.Car;
import telran.cars.service.model.CarOwner;
@Slf4j
@Service("carsService")
@Scope("prototype")
public class CarsServiceImpl implements CarsService {
HashMap<Long, CarOwner> owners = new HashMap<>();
HashMap<String, Car> cars = new HashMap<>();
HashMap<String, Integer> popularModels = new HashMap<>();
List<Map.Entry<String, Integer>> popularModelsSortedList = new ArrayList<>();
boolean isPopularMedelsSorted = false;
	@Override
	public PersonDto addPerson(PersonDto personDto) {
		long id = personDto.id();
		if(owners.containsKey(id)) {
			throw new IllegalStateException(String.format("person  %d already exists", id));
		}
		owners.put(id, new CarOwner(personDto));
		return personDto;
	}

	@Override
	public CarDto addCar(CarDto carDto) {
		String carNumber = carDto.number();
		if(cars.containsKey(carNumber)) {
			throw new IllegalStateException(String.format("car %s already exists", carNumber));
		}
		cars.put(carNumber, new Car(carDto));
		return carDto;
	}

	@Override
	public PersonDto updatePerson(PersonDto personDto) {
		long id = personDto.id();
		hasCarOwner(id);
		CarOwner carOwner = owners.get(id);
		carOwner.setEmail(personDto.email());
		return personDto;
	}

	@Override
	public PersonDto deletePerson(long id) {
		
		hasCarOwner(id);
		CarOwner carOwner = owners.get(id);
		List<Car> cars = carOwner.getCars();
		cars.forEach(c -> c.setOwner(null));
		owners.remove(id);
		return carOwner.build();
	}

	private void hasCarOwner(long id) {
		if(!owners.containsKey(id)) {
			throw new NotFoundException(String.format("person %d doesn't exists", id));
		}
	}

	@Override
	public CarDto deleteCar(String carNumber) {
		hasCar(carNumber);
		Car car = cars.get(carNumber);
		CarOwner carOwner = car.getOwner();
		
		carOwner.getCars().remove(car);
		cars.remove(carNumber);
		return car.build();
	}

	private void hasCar(String carNumber) {
		if(!cars.containsKey(carNumber)) {
			throw new NotFoundException(String.format("car %s doesn't exists", carNumber));
		}
	}

	@Override
	public TradeDealDto purchase(TradeDealDto tradeDeal) {
		log.debug("purchase: received car {}, owner {}", tradeDeal.carNumber(), tradeDeal.personId());
		Long personId = tradeDeal.personId();
		
		CarOwner carOwner = null;
		String carNumber = tradeDeal.carNumber();
		hasCar(carNumber);
		Car car = cars.get(carNumber);
		CarOwner oldOwner = car.getOwner();
		checkSameOwner(personId, oldOwner);
		if(oldOwner != null) {
			oldOwner.getCars().remove(car);
		}
		if(personId != null) {
			
			log.debug("new owner exists");
			hasCarOwner(personId);
			carOwner = owners.get(personId);
			carOwner.getCars().add(car);
		}
		car.setOwner(carOwner);
		Car currentCar =  cars.get(tradeDeal.carNumber());
		Integer popularModelCounter = popularModels.get(currentCar);
		popularModels.put(currentCar.getModel(), popularModelCounter++);
		isPopularMedelsSorted = false;
		return tradeDeal;
	}

	private void checkSameOwner(Long personId, CarOwner oldOwner) {
		if((oldOwner == null && personId == null) ||
				(oldOwner != null && personId == oldOwner.getId())) {
			throw new IllegalStateException("trade deal with same owner");
		}
		
	}

	@Override
	public List<CarDto> getOwnerCars(long id) {
		hasCarOwner(id);
		return owners.get(id).getCars().stream().map(Car::build).toList();
	}

	@Override
	public PersonDto getCarOwner(String carNumber) {
		hasCar(carNumber);
		Car car = cars.get(carNumber);
		CarOwner carOwner = car.getOwner();
		return carOwner != null ? carOwner.build() : null;
	}


	@Override
	public List<String> mostPopularModels() {
		
		if(!isPopularMedelsSorted) {
		popularModelsSortedList = new ArrayList<>(popularModels.entrySet());
		popularModelsSortedList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
		isPopularMedelsSorted = true;
		}
		return popularModelsSortedList.stream().map(e -> e.getKey()).collect(Collectors.toList()); 
	}
	
	
}
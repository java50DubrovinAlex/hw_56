package telran.cars.service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import telran.cars.dto.*;
import telran.cars.exceptions.NotFoundException;
import telran.cars.service.model.*;
@Service
@Slf4j
public class CarsServiceImpl implements CarsService {
HashMap<Long, CarOwner> owners = new HashMap<>();
HashMap<String, Car> cars = new HashMap<>();
	@Override
	public PersonDto addPerson(PersonDto personDto) {
		if(owners.containsKey(personDto.id())) {
			throw new IllegalStateException("Person whit id: " + personDto.id() + "already exist");
		}
		owners.put(personDto.id(), new CarOwner(personDto));
		log.debug("addPerson: received personData data: {}", personDto);
		return personDto;
	}
	public static void removeAll() {
		
	}
	@Override
	public CarDto addCar(CarDto carDto) {
		if(cars.containsKey(carDto.number())) {
			throw new IllegalStateException("Car with number: " + carDto.number() + "already exist");
		}
		cars.put(carDto.number(), new Car(carDto));
		log.debug("addCar: received car data: {}", carDto);
		return carDto;
	}

	@Override
	public PersonDto updatePerson(PersonDto personDto) {
		
		if(!owners.containsKey(personDto.id())) {
			throw new NotFoundException("Perosn with id: " + personDto.id() + "not found");
		}
		owners.get(personDto.id()).setEmail(personDto.email());
		log.debug("updatePerson: received personData data: {}", personDto);
		return personDto;
	}

	@Override
	public PersonDto deletePerson(long id) {
		if(!owners.containsKey(id)) {
			throw new NotFoundException("Person with id:" + id + "not found");
		}
		log.debug("delete person: person with ID {}", id);
		return owners.remove(id).build();
	}

	@Override
	public CarDto deleteCar(String carNumber) {
		if(!cars.containsKey(carNumber)) {
			throw new NotFoundException("Car with number: " + carNumber + "not found");
		}
		log.debug("delete person: person with ID {}", carNumber);
		return cars.remove(carNumber).build();
	}

	@Override
	public TradeDealDto purchase(TradeDealDto tradeDeal) {
		if(!cars.containsKey(tradeDeal.carNumber()) || !owners.containsKey(tradeDeal.personId())) {
			throw new NotFoundException("Car number: " + tradeDeal.carNumber() +  tradeDeal.personId() + "or person id dasent exist"); 
		}
		cars.get(tradeDeal.carNumber()).setOwner(owners.get(tradeDeal.personId()));
		owners.get(tradeDeal.personId()).getCars().add(cars.get(tradeDeal.carNumber()));
		log.debug("purchase: tradDeal with id{} and carNumber{}", tradeDeal.personId(), tradeDeal.carNumber());
		return tradeDeal;
	}

	@Override
	public List<CarDto> getOwnerCars(long id) {
		if(!owners.containsKey(id)) {
			throw new NotFoundException("Perosn with id: " + id + "not found");
		}
		List<CarDto> res = owners.get(id).getCars().stream().map(e -> new CarDto(e.getNumber(), e.getModel())).collect(Collectors.toList());
		log.debug("getOwnerCar: carDto list with id{}", id);
		return res;
	}

	@Override
	public PersonDto getCarOwner(String carNumber) {
		if(!cars.containsKey(carNumber)) {
			throw new NotFoundException("Car with number: " + carNumber + "not found");
		}
		PersonDto res = cars.get(carNumber).getOwner().build();
		log.debug("getCarOwner: personDto with person who owns car with carNumber{}", carNumber);
		return res;
	}

}
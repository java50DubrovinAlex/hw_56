package telran.cars.service.model;

import lombok.Getter;
import telran.cars.dto.CarDto;
import telran.cars.dto.PersonDto;

@Getter
public class TreadDeal {
	String carNumber;
	long ownerId;
	public TreadDeal(PersonDto personDto, CarDto carDto) {
		this.carNumber = carDto.number();
		this.ownerId = personDto.id();
		
	}
	
}

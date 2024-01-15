package telran.cars.service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.cars.dto.CarDto;
import telran.cars.dto.CarState;
import jakarta.persistence.*;
@Entity
@Getter
@Table(name = "cars")
@NoArgsConstructor

public class Car {
	@Id
	String number;
	@ManyToOne
	@JoinColumns({@JoinColumn(name = "model_name", nullable = false), 
		@JoinColumn(name = "model_year", nullable = false)})
	Model model;
	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = true)
	CarOwner carOwner;
	String color;
	Integer kilometers;
	@Enumerated(EnumType.STRING)
	CarState state;
	
	public static Car of(CarDto carDto) {
		Car car = new Car();
		car.number = carDto.number();
		car.model =  new Model();
		car.model.modelYear = new ModelYear(carDto.number(), carDto.modelYear());
		car.carOwner.id = carDto.ownerId();
		car.color = carDto.color();
		car.kilometers = carDto.Kilometers();
		car.state = carDto.carState();
		return car;
	}
	public CarDto build(Car car) {
		return new CarDto(car.number, car.color, car.kilometers,  car.state, car.model.modelYear.getName(), car.model.modelYear.getYear(), car.carOwner.getId());
	}
}
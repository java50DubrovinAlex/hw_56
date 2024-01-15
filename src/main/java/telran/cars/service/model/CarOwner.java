package telran.cars.service.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.cars.dto.PersonDto;
import jakarta.persistence.*;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name =  "car_owners")
public class CarOwner {
	@Id
	long id;
	String name;
	@Column(nullable = false, name = "birth_date")
	@Temporal(TemporalType.DATE)
	LocalDate birthDate;
	String email;
	public static CarOwner of(PersonDto personDto) {
		CarOwner carOwner = new CarOwner();
		carOwner.birthDate = LocalDate.parse(personDto.birthDate());
		carOwner.id = personDto.id();
		carOwner.name = personDto.name();
		carOwner.email = personDto.email();
		return carOwner;
	}
	public  PersonDto build(CarOwner carOwner) {
		return new PersonDto(carOwner.id, carOwner.name, carOwner.birthDate.toString(), carOwner.email );
	}
}

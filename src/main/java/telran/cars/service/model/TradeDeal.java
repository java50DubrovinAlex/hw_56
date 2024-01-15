package telran.cars.service.model;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import telran.cars.dto.TradeDealDto;
@Entity
@Table(name = "trade_deals")
@NoArgsConstructor
public class TradeDeal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	@ManyToOne
	@JoinColumn(name="car_number", nullable = false)
	Car car;
	@ManyToOne
	@JoinColumn(name="owner_id")
	CarOwner carOwner;
	@Temporal(TemporalType.DATE)
	LocalDate date;
	
	public static TradeDeal of(TradeDealDto tradeDealDto) {
		TradeDeal tradDeal = new TradeDeal();
		tradDeal.id = tradeDealDto.Id();
		tradDeal.car = new Car();
		tradDeal.car.number = tradeDealDto.carNumber();
		tradDeal.carOwner = new CarOwner();
		tradDeal.carOwner.id = tradeDealDto.personId();
		tradDeal.date = LocalDate.parse(tradeDealDto.date());
		
		return tradDeal;
	}
	
	public TradeDealDto builder(TradeDeal tradeDeal) {
		return new TradeDealDto(tradeDeal.car.number, tradeDeal.carOwner.id, tradeDeal.id, tradeDeal.date.toString());
	}
}
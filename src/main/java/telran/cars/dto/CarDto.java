package telran.cars.dto;
import jakarta.validation.constraints.*;
import static telran.cars.api.ValidationConstants.*;

import java.util.Objects;
public record CarDto(@NotEmpty(message=MISSING_CAR_NUMBER_MESSAGE)
	@Pattern(regexp = CAR_NUMBER_REGEXP,message=WRONG_CAR_NUMBER_MESSAGE) String number,
	@NotEmpty(message = MISS_CAR_COLOR_MESSAGE) String color, 
	@NotEmpty(message = MISS_CAR_KILOMETERS_MESSAGE) Integer Kilometers, 
	@NotEmpty(message = MISS_CAR_STATE_MESSAGE) CarState carState, 
	@NotEmpty(message = MISSING_CAR_MODEL_MESSAGE) String modelName, 
	@NotEmpty(message = MISSING_CAR_MODEL_YEAR_MESSAGE) Integer modelYear, 
	@NotNull(message=MISSING_PERSON_ID_MESSAGE)
	@Min(value=MIN_PERSON_ID_VALUE, message=WRONG_MIN_PERSON_ID_VALUE) @Max(value=MAX_PERSON_ID_VALUE,
	message=WRONG_MAX_PERSON_ID_VALUE ) Long ownerId ) {

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarDto other = (CarDto) obj;
		return Objects.equals(number, other.number);
	}
	
}

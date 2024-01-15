package telran.cars.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import static telran.cars.api.ValidationConstants.*;

public record ModelDto(
		@NotEmpty(message = MISSING_PERSON_NAME_MESSAGE) String name, 
		@NotNull(message=MISSING_CAR_MODEL_YEAR_MESSAGE)
		@Min(value=MIN_CAR_MODEL_YEAR_VALUE, message=WRONG_MIN_MODEL_YEAR_VALUE) @Max(value=MAX_CAR_MODEL_YEAR_VALUE,
		message=WRONG_MAX_MODEL_YEAR_VALUE ) Integer year, 
		@NotEmpty(message = MISSING_CAR_COMPANY_MESSAGE) String company, 
		@NotEmpty(message = MISSING_CAR_ENGINE_CAPACITY_MESSAGE) Integer engineCapacity, 
		@NotEmpty(message = MISSING_CAR_ENGINE_POWER) Integer engine_Power )  {

}

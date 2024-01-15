package telran.cars.dto;
import jakarta.validation.constraints.*;
import static telran.cars.api.ValidationConstants.*;

import java.time.LocalDate;
public record TradeDealDto(@NotEmpty(message=MISSING_CAR_NUMBER_MESSAGE)
@Pattern(regexp=CAR_NUMBER_REGEXP) String carNumber, 
@Min(value=MIN_PERSON_ID_VALUE, message=WRONG_MIN_PERSON_ID_VALUE) 
@Max(value=MAX_PERSON_ID_VALUE,message=WRONG_MAX_PERSON_ID_VALUE )Long personId,
@NotNull(message=MISSING_PERSON_ID_MESSAGE)
@Min(value=MIN_PERSON_ID_VALUE, message=WRONG_MIN_PERSON_ID_VALUE) 
@Max(value=MAX_PERSON_ID_VALUE,message=WRONG_MAX_PERSON_ID_VALUE )Long Id,
@NotEmpty(message = MISSING_TRADE_DEAL_DATE_MESSAGE) @Pattern(regexp=BIRTH_DATE_REGEXP, message=WRONG_DATE_FORMAT)String date) {

}

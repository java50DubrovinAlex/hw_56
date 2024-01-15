package telran.cars.api;

public interface ValidationConstants {
	String MISSING_CAR_NUMBER_MESSAGE = "Missing car number";
	String CAR_NUMBER_REGEXP = "(\\d{3}-\\d{2}-\\d{3})|(\\d{2}-\\d{3}-\\d{2})";
	String WRONG_CAR_NUMBER_MESSAGE = "Incorrect Car Number";
	String MISSING_CAR_MODEL_MESSAGE = "Missing car model";
	String MISSING_PERSON_ID_MESSAGE = "Missing person ID";
	long MIN_PERSON_ID_VALUE = 100000l;
	long MAX_PERSON_ID_VALUE = 999999l;
	String WRONG_MIN_PERSON_ID_VALUE = "Person ID must be greater or equal " + MIN_PERSON_ID_VALUE;
	String WRONG_MAX_PERSON_ID_VALUE = "Person ID must be less or equal " + MAX_PERSON_ID_VALUE;
	String MISSING_PERSON_NAME_MESSAGE = "Missing person name";
	String MISSING_BIRTH_DATE_MESSAGE = "Missing person's birth date";
	String BIRTH_DATE_REGEXP = "\\d{4}-\\d{2}-\\d{2}";
	String WRONG_DATE_FORMAT = "Wrong date format, must be YYYY-MM-dd";
	String MISSING_PERSON_EMAIL = "Missing email address";
	String WRONG_EMAIL_FORMAT = "Wrong email format";
	String MISS_CAR_COLOR_MESSAGE = "Missing car color";
	String MISS_CAR_KILOMETERS_MESSAGE = "Missing car kilometers";
	String MISS_CAR_STATE_MESSAGE = "Missing car state";
	String MISSING_CAR_MODEL_YEAR_MESSAGE = "Missing car model year";
	int MIN_CAR_MODEL_YEAR_VALUE = 1970;
	int MAX_CAR_MODEL_YEAR_VALUE = 2023;
	String WRONG_MIN_MODEL_YEAR_VALUE = "Model year must be greater or equal " + MIN_CAR_MODEL_YEAR_VALUE;
	String WRONG_MAX_MODEL_YEAR_VALUE = "Model year must be less or equal " + MAX_CAR_MODEL_YEAR_VALUE;
	String MISSING_CAR_COMPANY_MESSAGE = "Missing car company";
	String MISSING_CAR_ENGINE_CAPACITY_MESSAGE = "Missing car engine capacity";
	String MISSING_CAR_ENGINE_POWER = "Missing car engine power";
	String MISSING_TRADE_DEAL_DATE_MESSAGE = "Missing trade deal date";
	
}

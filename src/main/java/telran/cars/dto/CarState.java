package telran.cars.dto;

import jakarta.validation.constraints.NotNull;

public enum CarState {
@NotNull OLD, 
@NotNull NEW, 
@NotNull GOOD, 
@NotNull MIDDLE, 
@NotNull BAD
}

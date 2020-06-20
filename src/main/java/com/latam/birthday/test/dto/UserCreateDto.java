package com.latam.birthday.test.dto;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserCreateDto {

    @NotNull
    @NotBlank
    @Pattern(regexp="^[A-Za-z ]*$",message = "Invalid Input")
    private String firstNames;

    @NotNull
    @NotBlank
    @Pattern(regexp="^[A-Za-z ]*$",message = "Invalid Input")
    private String lastNames;

    @NotNull
    @Pattern(regexp="^\\d{2}-\\d{2}-\\d{4}$",message = "Invalid Date format (dd-MM-yyyy)")
    private String birthDate;

    public String getFirstNames() {
        return firstNames;
    }

    public void setFirstNames(String firtNames) {
        this.firstNames = firtNames;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}

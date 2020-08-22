package com.ssau.Hostel7.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssau.Hostel7.constants.PatternsConstants;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@Data
public class RegisterResidentRequestDto {

    /**
     * Номер договора.
     */
    @JsonProperty("contract_id_number")
    @NotNull
    private String contractIdNumber;

    /**
     * Идентификатор заселяемого.
     */
    @Pattern(regexp = PatternsConstants.UUID_PATTERN)
    @NotNull
    @JsonProperty("settler_id")
    private String settlerId;

}

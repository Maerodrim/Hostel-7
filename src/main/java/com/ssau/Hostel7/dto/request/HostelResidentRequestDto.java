package com.ssau.Hostel7.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssau.Hostel7.constants.PatternsConstants;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Dto заселённого.
 */
@AllArgsConstructor
@Data
public class HostelResidentRequestDto {

    /**
     * Идентификатор заселённого.
     */
    @Pattern(regexp = PatternsConstants.UUID_PATTERN)
    private String id;

    /**
     * Номер студенческого билета.
     */
    @JsonProperty("student_id_number")
    private String studentIDNumber;

    /**
     * Номер договора.
     */
    @NotNull
    @JsonProperty("contract_id_number")
    private String contractIdNumber;

    /**
     * Номер группы.
     */
    @NotNull
    @JsonProperty("group_number")
    private String groupNumber;

    /**
     * Номер пропуска.
     */
    @JsonProperty("pass_number")
    private String passNumber;

    /**
     * Идентификатор заселяемого.
     */
    @Pattern(regexp = PatternsConstants.UUID_PATTERN)
    @NotNull
    @JsonProperty("settler_id")
    private String settlerId;

}

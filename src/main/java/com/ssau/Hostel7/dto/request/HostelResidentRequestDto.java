package com.ssau.Hostel7.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssau.Hostel7.constants.PatternsConstants;
import com.ssau.Hostel7.helper.validation.group.CreateHostelResidentValidationGroup;
import com.ssau.Hostel7.helper.validation.group.UpdateHostelResidentValidationGroup;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
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
    @NotNull(groups = UpdateHostelResidentValidationGroup.class)
    @Null(groups = CreateHostelResidentValidationGroup.class)
    private String id;

    /**
     * Номер студенческого билета.
     */
    @JsonProperty("student_id_number")
    @NotNull(groups = UpdateHostelResidentValidationGroup.class)
    private String studentIDNumber;

    /**
     * Номер договора.
     */
    @JsonProperty("contract_id_number")
    @NotNull(groups = UpdateHostelResidentValidationGroup.class)
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
    @NotNull(groups = UpdateHostelResidentValidationGroup.class)
    private String passNumber;

    /**
     * Идентификатор заселяемого.
     */
    @Pattern(regexp = PatternsConstants.UUID_PATTERN)
    @NotNull
    @JsonProperty("settler_id")
    private String settlerId;

}

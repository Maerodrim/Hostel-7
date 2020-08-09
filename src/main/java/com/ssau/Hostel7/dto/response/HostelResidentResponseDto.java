package com.ssau.Hostel7.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HostelResidentResponseDto {

    /**
     * Идентификатор заселённого.
     */
    private String id;

    /**
     * Номер студенческого билета.
     */
    @JsonProperty("student_id_number")
    private String studentIDNumber;

    /**
     * Номер договора.
     */
    @JsonProperty("contract_id_number")
    private String contractIdNumber;

    /**
     * Номер группы.
     */
    @JsonProperty("group_number")
    private String groupNumber;

    /**
     * Номер пропуска.
     */
    @JsonProperty("pass_number")
    private String passNumber;

    @JsonProperty("profile_id")
    private String profileId;

}

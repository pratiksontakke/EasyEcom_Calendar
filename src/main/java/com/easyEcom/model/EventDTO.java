package com.easyEcom.model;

import com.google.api.client.util.DateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {

    @NotNull
    @NotBlank
    @NotEmpty
    private String summary;
    @NotNull
    @NotBlank
    @NotEmpty
    private String location;
    @NotNull
    @NotBlank
    @NotEmpty
    private String description;
    private String[] attendees;
    @NotNull
    @NotBlank
    @NotEmpty
    private String startTime;
    @NotNull
    @NotBlank
    @NotEmpty
    private String endTime;

}

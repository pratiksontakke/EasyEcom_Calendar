package com.easyEcom.model;

import com.google.api.client.util.DateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {

    private String summary;
    private String location;
    private String description;
    private String[] attendees;
    private String startTime;
    private String endTime;

}

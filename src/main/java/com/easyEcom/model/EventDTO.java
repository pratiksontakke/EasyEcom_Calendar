package com.easyEcom.model;

import com.google.api.client.util.DateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityScan
public class EventDTO {

    private String summary;
    private String location;
    private String description;
    private String[] attendees;
    private String startTime;
    private String endTime;

}

package com.api.pojo.graphQl.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Variables {
    private String locationName;
    private String locationType;
    private String locationDimension;
    private Integer locationId;
    private List<Integer> locationIds;
    private String characterName;
    private String characterType;
    private String characterStatus;
    private String species;
    private String gender;
    private String image;
    private Integer originId;
    private List<Integer> characterIds;
    private Integer characterId;
    private String episodeName;
    private String air_date;
    private String episodeCustomId;
    private List<Integer> episodeIds;
    private Integer episodeId;
}

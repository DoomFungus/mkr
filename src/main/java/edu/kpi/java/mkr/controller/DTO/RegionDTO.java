package edu.kpi.java.mkr.controller.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.kpi.java.mkr.model.Author;
import edu.kpi.java.mkr.model.Book;
import edu.kpi.java.mkr.model.Region;
import lombok.Data;

import java.util.stream.Collectors;

@Data
public class RegionDTO {
    @JsonProperty("id")
    private int regionId;
    @JsonProperty("name")
    private String regionName;

    public static RegionDTO from(Region region){
        RegionDTO res = new RegionDTO();
        res.regionId = region.getRegionId();
        res.regionName = region.getRegionName();
        return res;
    }
}

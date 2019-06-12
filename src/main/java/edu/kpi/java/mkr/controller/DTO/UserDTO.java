package edu.kpi.java.mkr.controller.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.kpi.java.mkr.model.User;

import java.util.List;

public class UserDTO {
    @JsonProperty("id")
    private int userId;
    @JsonProperty("name")
    private String userName;
    @JsonProperty("region")
    private RegionDTO region;
    @JsonProperty("roles")
    private List<String> roles;

    public static UserDTO from(User user){
        UserDTO res = new UserDTO();
        res.userId = user.getUserId();
        res.userName = user.getUsername();
        res.region = RegionDTO.from(user.getRegion());
        res.roles = user.getRoles();
        return res;
    }
}

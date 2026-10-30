package dtosJSON;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class HockeyClub {
    @JsonProperty("members")
    private ArrayList<Member> members;

    public HockeyClub() {
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }
}

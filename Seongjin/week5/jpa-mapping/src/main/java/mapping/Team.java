package mapping;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

@Entity
public class Team {
    @Id @GeneratedValue
    @Column(name="TEAM_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    @OneToMany(mappedBy = "team") //가짜 매핑 (읽기 전용 거울 매핑) -> 주인의 반대편
    private List<Member> members = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

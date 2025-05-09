package mapping;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    @Column(name="NAME")
    private String name;

    @OneToMany(mappedBy = "team") // 가짜 매핑 (읽기 전용 거울 매핑) -> 주인의 반대편
    private List<Member> members = new ArrayList<>(); // 이렇게 new ArrayList<>()를 해주는 것은 관례이다.

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

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

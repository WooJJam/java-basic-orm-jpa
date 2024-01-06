package hellojpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<Member> getMembers() {
        return members;
    }

    @OneToMany(mappedBy = "team") // member 클래스의 team 변수와 매핑
    // 주인에게 mappedBy 된다라고 생각하자
    // 주인은 누구? 외래키가 있는 곳!
    private List<Member> members = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
//@SequenceGenerator(
//        name = "MEMBER_SEQ_GENERATOR",
//        sequenceName = "MEMBER_SEQ", // 매핑할 시퀀스 이름
//        initialValue = 1, allocationSize = 1
//)
//@TableGenerator(
//        name = "MEMBER_SEQ_GENERATOR",
//        table = "MY_SEQUENCES",
//        pkColumnName = "MEMBER_SEQ", allocationSize = 1)
public class Member extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    다대일 [N:1]
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "TEAM_ID")
//    private Team team;

    // 일대다 [1:N] 양방향.. 야매 방식임..
    @ManyToOne
    @JoinColumn(name="TEAM_ID", insertable = false, updatable = false)
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();
    public void setId(Long id) {
        this.id = id;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

    public Team getTeam() {
        return team;
    }

    public Locker getLocker() {
        return locker;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Member 객체에 team 설정할때 Team객체도 같이 설정해주면 안 까먹을 수 있다!
//    public void changeTeam (Team team) {
//        this.team = team;
//        team.getMembers().add(this);
//    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

//    public Team getTeam() {
//        return team;
}


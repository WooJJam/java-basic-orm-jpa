package hellojpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Embedded
    // 기간
    private Period workPeriod;

    @Embedded
    private Address homeAddress;

    @ElementCollection
    @CollectionTable(name="FAVORITE_FOOD", joinColumns =
        @JoinColumn(name = "MEMBER_ID")
    )
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }


//    @ElementCollection
//    @CollectionTable(name="ADDRESS", joinColumns =
//        @JoinColumn(name = "MEMBER_ID")
//    )
//    private List<Address> addressHistory = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="MEMBER_ID")
    private List<AddressEntity> addressHistory = new ArrayList<>();

    public List<AddressEntity> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<AddressEntity> addressHistory) {
        this.addressHistory = addressHistory;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
    //    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "city",
//                    column = @Column(name = "WORK_CITY")),
//            @AttributeOverride(name = "street",
//                    column = @Column(name = "WORK_STREET")),
//            @AttributeOverride(name = "zipcode",
//                    column = @Column(name = "WORK_ZIPCODE"))
//    })
//    private Address workAddress;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

//    public Address getWorkAddress() {
//        return workAddress;
//    }
//
//    public void setWorkAddress(Address workAddress) {
//        this.workAddress = workAddress;
//    }
    ////    다대일 [N:1]
////    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "TEAM_ID")
////    private Team team;
//
    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩
//    @ManyToOne(fetch = FetchType.EAGER) // 즉시 로딩
    // 즉시 로딩은 JPQL 에서 N+1 문제를 야기
    @JoinColumn()
    private Team team;
//
////    @OneToOne
////    @JoinColumn(name = "LOCKER_ID")
////    private Locker locker;
////
////    @OneToMany(mappedBy = "member")
////    private List<MemberProduct> memberProducts = new ArrayList<>();
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setTeam(Team team) {
//        this.team = team;
//    }
//
////    public void setLocker(Locker locker) {
////        this.locker = locker;
////    }
//
//    public Team getTeam() {
//        return team;
//    }
//
////    public Locker getLocker() {
////        return locker;
////    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    // Member 객체에 team 설정할때 Team객체도 같이 설정해주면 안 까먹을 수 있다!
////    public void changeTeam (Team team) {
////        this.team = team;
////        team.getMembers().add(this);
////    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
////    public Team getTeam() {
////        return team;
}


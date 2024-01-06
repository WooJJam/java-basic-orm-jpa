package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ", // 매핑할 시퀀스 이름
        initialValue = 10, allocationSize = 1
)
public class Member {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // 키본 키 생성을 데이터베이스에 위임
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    public Long getId() {
        return id;
    }

    public Member() {
    }

    public String getUsername() {
        return username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //    @Column(name = "name", nullable = false, updatable = false, columnDefinition = "varchar(100) default 'EMPTY'")
//    private String username;
//
//    private Integer age;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "role_type")
//    private RoleType roleType;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;
//
//    private LocalDate testLocalDate;
//    private LocalDateTime testLocalDateTime;
//
//    @Lob
//    private String description;
//
//    @Transient
//    private int temp;
//
//    public Member() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public Integer getAge() {
//        return age;
//    }
//
//    public RoleType getRoleType() {
//        return roleType;
//    }
//
//    public Date getCreateDate() {
//        return createDate;
//    }
//
//    public Date getLastModifiedDate() {
//        return lastModifiedDate;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public int getTemp() {
//        return temp;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//
//    public void setRoleType(RoleType roleType) {
//        this.roleType = roleType;
//    }
//
//    public void setCreateDate(Date createDate) {
//        this.createDate = createDate;
//    }
//
//    public void setLastModifiedDate(Date lastModifiedDate) {
//        this.lastModifiedDate = lastModifiedDate;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public void setTemp(int temp) {
//        this.temp = temp;
//    }
}

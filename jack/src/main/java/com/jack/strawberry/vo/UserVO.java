package com.jack.strawberry.vo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserVO {
    @Id(autoincrement = true)
    private Long user_id;
    private int score;
    private String sex;
    private String name;
    @Generated(hash = 1479889321)
    public UserVO(Long user_id, int score, String sex, String name) {
        this.user_id = user_id;
        this.score = score;
        this.sex = sex;
        this.name = name;
    }
    @Generated(hash = 769280242)
    public UserVO() {
    }
    public Long getUser_id() {
        return this.user_id;
    }
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    public int getScore() {
        return this.score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

package com.jack.strawberry.vo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class StoreVO {
    @Id(autoincrement = true)
    private Long id;
    private int score;
    private String name;
    @Generated(hash = 228600730)
    public StoreVO(Long id, int score, String name) {
        this.id = id;
        this.score = score;
        this.name = name;
    }
    @Generated(hash = 207915455)
    public StoreVO() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getScore() {
        return this.score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

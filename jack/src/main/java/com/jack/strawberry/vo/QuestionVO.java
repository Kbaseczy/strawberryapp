package com.jack.strawberry.vo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class QuestionVO {
    @Id(autoincrement = true)
    private Long id;
    private String content_question;
    private String anwser_a,anwser_b;
    private String anwser_real;
    @Generated(hash = 1184132181)
    public QuestionVO(Long id, String content_question, String anwser_a,
            String anwser_b, String anwser_real) {
        this.id = id;
        this.content_question = content_question;
        this.anwser_a = anwser_a;
        this.anwser_b = anwser_b;
        this.anwser_real = anwser_real;
    }
    @Generated(hash = 1900878688)
    public QuestionVO() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContent_question() {
        return this.content_question;
    }
    public void setContent_question(String content_question) {
        this.content_question = content_question;
    }
    public String getAnwser_a() {
        return this.anwser_a;
    }
    public void setAnwser_a(String anwser_a) {
        this.anwser_a = anwser_a;
    }
    public String getAnwser_b() {
        return this.anwser_b;
    }
    public void setAnwser_b(String anwser_b) {
        this.anwser_b = anwser_b;
    }
    public String getAnwser_real() {
        return this.anwser_real;
    }
    public void setAnwser_real(String anwser_real) {
        this.anwser_real = anwser_real;
    }
}

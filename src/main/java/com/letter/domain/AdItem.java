package com.letter.domain;

import org.hibernate.validator.constraints.NotEmpty;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.*;
import javax.validation.constraints.Size;



@Entity
@Table(name = "AD_ITEM")
public class AdItem {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "AD_ID")
    private Long id;

    @Column(name = "AD_NUM", unique = true)
    @NotEmpty(message = "NotEmpty.ad.num")
    @Size(max = 10, message = "Size.ad.num")
    private String num;

    @Column(name = "AD_VALUE")
    @NotEmpty(message = "NotEmpty.ad.value")
    @Size(max = 100, message = "Size.ad.value")
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

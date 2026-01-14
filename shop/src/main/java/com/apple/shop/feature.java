package com.apple.shop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class feature {

    private String name;
    private Integer age;
    public void 한살더하기(){
        this.age = this.age + 1;
    }
    public void 나이설정(Integer a){
        if (a>0 && a<100) {
            this.age = a;
        }

    }

}

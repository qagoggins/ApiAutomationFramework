package io.framework.Pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(setterPrefix = "set")
@Getter
public class Employee {

    private String id;
    @Setter
    private String fname;
    private String lname;
}

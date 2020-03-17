package com.example.travel.request;

import lombok.Data;

import javax.annotation.security.DenyAll;
import java.io.Serializable;

@Data
public class ThreeInOneRequest implements Serializable {

    private static final long serialVersionUID = 4408408969907531298L;

    private Integer choose;

    private Integer pageNum = 1;
}

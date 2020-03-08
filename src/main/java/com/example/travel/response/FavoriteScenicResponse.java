package com.example.travel.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FavoriteScenicResponse implements Serializable {

    private static final long serialVersionUID = 4747667155684074231L;

    private String nickName;

    private Date createTime;

}

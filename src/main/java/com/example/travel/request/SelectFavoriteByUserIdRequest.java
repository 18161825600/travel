package com.example.travel.request;


import lombok.Data;

import java.io.Serializable;

@Data
public class SelectFavoriteByUserIdRequest implements Serializable {

    private static final long serialVersionUID = -2891696096080513717L;

    private Long id;

    private Integer pageNum = 1;
}

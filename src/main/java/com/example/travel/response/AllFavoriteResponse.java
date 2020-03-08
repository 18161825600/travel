package com.example.travel.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AllFavoriteResponse implements Serializable {

    private static final long serialVersionUID = 6610561753123451294L;

    private List<FavoriteResponse> favoriteResponseList;

    private Integer total;
}

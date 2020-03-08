package com.example.travel.request;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinitions;
import java.io.Serializable;

@Data
public class UpdateShopCarNumRequest implements Serializable {

    private Long Id;

    private Integer number;
}

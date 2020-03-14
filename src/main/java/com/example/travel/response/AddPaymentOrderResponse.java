package com.example.travel.response;

import lombok.Data;

import javax.annotation.security.DenyAll;
import java.io.Serializable;
import java.util.List;

@Data
public class AddPaymentOrderResponse implements Serializable {
    private static final long serialVersionUID = 8121911908416441995L;

    private List<Long> ids;
}

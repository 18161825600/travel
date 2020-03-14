package com.example.travel.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class CallWhatResponse implements Serializable {
    private static final long serialVersionUID = 5549916654091493391L;

    private String msg;
}

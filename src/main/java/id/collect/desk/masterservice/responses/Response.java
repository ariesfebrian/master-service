package id.collect.desk.masterservice.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {
    private String service;
    private String message;
    private T data;
}

package com.dummy.project.base.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response<T> {

    private Boolean succeed;
    private Boolean error;
    private T Result;
}

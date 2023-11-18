package com.luv.urlshortener.dao.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpResponse {
    private String userId;
}

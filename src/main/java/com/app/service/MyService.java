package com.app.service;

import com.app.dto.AddShortLinkRequest;
import com.app.dto.AddShortLinkResponse;
import org.springframework.stereotype.Component;


public interface MyService {
    AddShortLinkResponse getShortLinkResponse();
}

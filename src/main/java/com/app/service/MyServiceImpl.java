package com.app.service;

import com.app.dto.AddShortLinkResponse;
import org.springframework.stereotype.Service;

@Service
public class MyServiceImpl implements  MyService {
    @Override
    public AddShortLinkResponse getShortLinkResponse() {
        return new AddShortLinkResponse("original");
    }
}

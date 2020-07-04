package com.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddShortLinkResponse {
    private String link;

    public AddShortLinkResponse(String link) {
        this.link = link;
    }
}

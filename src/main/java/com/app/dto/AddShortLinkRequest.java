package com.app.dto;

import java.util.Objects;

public class AddShortLinkRequest {


    private String original;

    public AddShortLinkRequest(){

    }

    public AddShortLinkRequest(String original) {
        this.original = original;
    }

    public String getOriginal() {
        return original;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddShortLinkRequest request = (AddShortLinkRequest) o;
        return Objects.equals(original, request.original);
    }

    @Override
    public int hashCode() {
        return Objects.hash(original);
    }

}

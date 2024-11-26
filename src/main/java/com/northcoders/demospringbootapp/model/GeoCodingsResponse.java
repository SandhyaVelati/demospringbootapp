package com.northcoders.demospringbootapp.model;

import java.util.List;

public record GeoCodingsResponse(List<Coordinates> results) {
}

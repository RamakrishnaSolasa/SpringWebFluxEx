package com.Graphql.springWebFlux_GraphQL.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StocksInput {

    @JsonProperty("stockName")
    private String stockName;
    @JsonProperty("stockPrice")
    private Integer stockPrice;
    @JsonProperty("stockCategory")
    private String stockCategory;
}

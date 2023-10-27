package com.Graphql.springWebFlux_GraphQL.service;

import com.Graphql.springWebFlux_GraphQL.model.Stocks;
import com.Graphql.springWebFlux_GraphQL.model.StocksInput;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StocksService {

    Mono<Stocks> getStocksById(Integer id);
    Mono<Stocks> getStocksByName(String stockName);
    Flux<Stocks> getAllStocks();
    Flux<Stocks> getStocksByCategory(String club);
    Mono<Stocks> addStocks(StocksInput stocksInput);
    Mono<Stocks> updateStocks(Integer id, StocksInput playerInput);
    Mono<Stocks> deleteStocksById(Integer id);
    Mono<Stocks> deleteStocksByName(String stockName);
}

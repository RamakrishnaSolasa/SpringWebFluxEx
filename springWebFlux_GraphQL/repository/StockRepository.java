package com.Graphql.springWebFlux_GraphQL.repository;

import com.Graphql.springWebFlux_GraphQL.model.Stocks;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StockRepository extends ReactiveCrudRepository<Stocks,Integer> {

    Mono<Stocks> findByStockName(String name);
    Mono<Stocks> deleteByStockName(String name);
    Flux<Stocks> findByStockCategory(String club);
}

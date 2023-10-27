package com.Graphql.springWebFlux_GraphQL.controller;

import com.Graphql.springWebFlux_GraphQL.model.Stocks;
import com.Graphql.springWebFlux_GraphQL.model.StocksInput;
import com.Graphql.springWebFlux_GraphQL.service.StocksService;
import java.util.logging.Level;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

@Controller
@Slf4j
public class StocksController {


    private final StocksService stocksService;

    @Autowired
    public StocksController(StocksService stocksService) {
        this.stocksService = stocksService;
    }

    @QueryMapping("getAllStocks")
    Flux<Stocks> getAllStocks() {
        log.info("Get all stocks using 'getAllStocks' query");
        return this.stocksService.getAllStocks().log();
    }

    @MutationMapping("addStocks")
    Mono<Stocks> addStocks(@Argument StocksInput stockInput) {
        log.info("Add stocks using 'addStocks' mutation");
        return this.stocksService.addStocks(stockInput).log();
    }

    @MutationMapping("deleteStocksById")
    Mono<Stocks> deleteStocksById(@Argument Integer id) {
        log.info("Delete stocks using 'deleteStocksById' mutation");
        return this.stocksService.deleteStocksById(id).log();
    }
}

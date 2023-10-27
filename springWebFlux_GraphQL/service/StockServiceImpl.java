package com.Graphql.springWebFlux_GraphQL.service;

import com.Graphql.springWebFlux_GraphQL.exception.EntityMappingException;
import com.Graphql.springWebFlux_GraphQL.model.Stocks;
import com.Graphql.springWebFlux_GraphQL.model.StocksInput;
import com.Graphql.springWebFlux_GraphQL.repository.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@Service
public class StockServiceImpl implements StocksService {

    private final StockRepository stockRepository;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Flux<Stocks> getAllStocks() {
        final String errorMessage = "There is an issue getting all of stocks.";
        return processWithErrorCheck(this.stockRepository.findAll(), errorMessage);
    }

    public Mono<Stocks> getStocksById(Integer id) {
        final String errorMessage = String.format("There is no Stock with id: '%d'", id);
        return processWithErrorCheck(this.stockRepository.findById(id), errorMessage);
    }

    public Mono<Stocks> getStocksByName(String stockName) {
        final String errorMessage = String.format("There is no Stocks with name: '%s'", stockName);
        return processWithErrorCheck(this.stockRepository.findByStockName(stockName), errorMessage);
    }

    public Flux<Stocks> getStocksByCategory(String stockCategory) {
        final String errorMessage = String.format("There is no players for club: '%s'", stockCategory);
        return processWithErrorCheck(this.stockRepository.findByStockCategory(stockCategory), errorMessage);
    }

//    public Flux<Stocks> getPlayersByNationality(String nationality) {
//        final String errorMessage = String
//                .format("There is no players with nationality: '%s'", nationality);
//        return processWithErrorCheck(this.stockRepository.findByNationality(nationality),
//                errorMessage);
//    }

    public Mono<Stocks> addStocks(StocksInput stocksInput) {
        final String errorMessage = "Unable to add stocks with input:" + stocksInput;
        return processWithErrorCheck(this.stockRepository.save(new Stocks(stocksInput)), errorMessage);
    }

    @Override
    public Mono<Stocks> updateStocks(Integer id, StocksInput playerInput) {
        return null;
    }

//    public Mono<Stocks> updateStocks(@Argument Integer id, @Argument StocksInput stocksInput) {
//        final String errorMessage =
//                "Unable to update stocks with id" + id + "input:" + stocksInput;
//        return processWithErrorCheck(this.stockRepository.findById(Objects.requireNonNull(id)),
//                errorMessage)
//                .flatMap(stocks -> {
//                    stocks.setStockName(Objects.requireNonNull(stocksInput.getStockName()))
//                            .setStockName(Objects.requireNonNull(stocksInput.getStockName()))
//                            .setStockPrice(Objects.requireNonNull(stocksInput.getStockPrice()))
//                            .setStockCategory(Objects.requireNonNull(stocksInput.getStockCategory()))
//                            .setStockCategory(Objects.requireNonNull(stocksInput.getStockCategory()));
//                    return this.stockRepository.save(stocks).log();
//                });
//    }

    @Override
    public Mono<Stocks> deleteStocksById(Integer id) {
        return getStocksById(id).map(stocks -> {
            this.stockRepository.deleteById(id).subscribe();
            return stocks;
        });
    }

    @Override
    public Mono<Stocks> deleteStocksByName(String stockName) {
        return getStocksByName(stockName).map(stocks -> {
            this.stockRepository.deleteByStockName(stockName).subscribe();
            return stocks;
        });
    }

    private <T> Mono<T> processWithErrorCheck(Mono<T> monoToCheck, String errorMessage) {
        return monoToCheck.switchIfEmpty(Mono.defer(() -> {
            log.error(errorMessage);
            return Mono.error(new EntityMappingException(errorMessage));
        }));
    }

    private <T> Flux<T> processWithErrorCheck(Flux<T> fluxToCheck, String errorMessage) {
        return fluxToCheck.switchIfEmpty(Flux.defer(() -> {
            log.error(errorMessage);
            return Flux.error(new EntityMappingException(errorMessage));
        }));
    }
}

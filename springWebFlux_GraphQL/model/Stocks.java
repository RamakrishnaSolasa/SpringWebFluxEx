package com.Graphql.springWebFlux_GraphQL.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Stocks {


        @Id
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("stockName")
        private String stockName;
        @JsonProperty("stockPrice")
        private Integer stockPrice;
        @JsonProperty("stockCategory")
        private String stockCategory;

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getStockName() {
                return stockName;
        }

        public void setStockName(String stockName) {
                this.stockName = stockName;
        }

        public Integer getStockPrice() {
                return stockPrice;
        }

        public void setStockPrice(Integer stockPrice) {
                this.stockPrice = stockPrice;
        }

        public String getStockCategory() {
                return stockCategory;
        }

        public void setStockCategory(String stockCategory) {
                this.stockCategory = stockCategory;
        }

        public Stocks(StocksInput stocksInput) {
                this.stockName = Objects.requireNonNull(stocksInput.getStockName());
                this.stockPrice = Objects.requireNonNull(stocksInput.getStockPrice());
                this.stockCategory = Objects.requireNonNull(stocksInput.getStockCategory());
        }

}

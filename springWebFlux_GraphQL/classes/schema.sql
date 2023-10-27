CREATE TABLE account(
     id INT PRIMARY KEY AUTO_INCREMENT,
     name VARCHAR(255),
     years INT,
     category VARCHAR(255)
);

CREATE TABLE holdings(
     id VARCHAR(255) PRIMARY KEY,
     type VARCHAR(255),
     top VARCHAR(255),
     account_id VARCHAR(255) UNIQUE NOT NULL,

     CONSTRAINT account_holdings_fk FOREIGN KEY(account_id) REFERENCES account(id)
);
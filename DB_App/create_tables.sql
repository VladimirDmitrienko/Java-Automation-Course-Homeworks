CREATE TABLE users (
    user_id INT(10) NOT NULL,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(255),
    PRIMARY KEY(user_id)
);
CREATE TABLE accounts (
    account_id INT(10) NOT NULL,
    user_id INT(10) NOT NULL,
    balance DECIMAL(16,3) NOT NULL,
	currency VARCHAR(10) NOT NULL,
    PRIMARY KEY(account_id),
    FOREIGN KEY(user_id) REFERENCES users (user_id)
);
CREATE TABLE transactions (
    transaction_id INT(10) NOT NULL,
    account_id INT(10) NOT NULL,
    amount DECIMAL(15,3) NOT NULL,
    PRIMARY KEY(transaction_id),
    FOREIGN KEY(account_id) REFERENCES accounts (account_id)
);
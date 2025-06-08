-- src/main/resources/sql-scripts/add-data.sql

-- Sample Accounts (10 total) – let H2 auto-generate the IDs
INSERT INTO accounts (holder_name, balance, created_at)
VALUES ('Alice',   100.00, CURRENT_TIMESTAMP());
INSERT INTO accounts (holder_name, balance, created_at)
VALUES ('Bob',      50.00, CURRENT_TIMESTAMP());
INSERT INTO accounts (holder_name, balance, created_at)
VALUES ('Charlie',  75.50, CURRENT_TIMESTAMP());
INSERT INTO accounts (holder_name, balance, created_at)
VALUES ('Diana',   200.00, CURRENT_TIMESTAMP());
INSERT INTO accounts (holder_name, balance, created_at)
VALUES ('Ethan',   125.25, CURRENT_TIMESTAMP());
INSERT INTO accounts (holder_name, balance, created_at)
VALUES ('Fiona',   300.00, CURRENT_TIMESTAMP());
INSERT INTO accounts (holder_name, balance, created_at)
VALUES ('George',   10.00, CURRENT_TIMESTAMP());
INSERT INTO accounts (holder_name, balance, created_at)
VALUES ('Hannah',  500.75, CURRENT_TIMESTAMP());
INSERT INTO accounts (holder_name, balance, created_at)
VALUES ('Ian',      60.60, CURRENT_TIMESTAMP());
INSERT INTO accounts (holder_name, balance, created_at)
VALUES ('Julia',   420.00, CURRENT_TIMESTAMP());

-- Sample Transaction (Alice → Bob) – IDs auto-generated
INSERT INTO transactions (from_account_id, to_account_id, amount, timestamp)
VALUES (1, 2, 30.00, CURRENT_TIMESTAMP());


CREATE SEQUENCE forecast_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE forecast (
                           id bigint DEFAULT nextval('forecast_id_seq') PRIMARY KEY,
                           temperature DECIMAL,
                           exchange_rate DECIMAL,
                           holiday_coefficient DECIMAL,
                           payday_coefficient DECIMAL,
                           predicted_orders INT,
                           forecast_date DATE,
                           actual_orders INT
);

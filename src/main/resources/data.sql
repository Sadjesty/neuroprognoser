DELETE FROM forecast;

INSERT INTO forecast (temperature, exchange_rate, holiday_coefficient, payday_coefficient, predicted_orders, forecast_date, actual_orders)
VALUES
    (25.0, 1.2, 0.8, 1.1, 150.0, '2023-05-15', 100),
    (23.5, 1.3, 0.9, 1.0, 120.0, '2023-05-16', 90),
    (20.0, 1.5, 0.7, 0.9, 80.0, '2023-05-17', 80);
Operations

The assignment used in-memory H2 database

1. curl --location --request POST 'localhost:8080/assignment/stockdata/task/upload' \
--form 'file=@"/C:/Users/4603086/Downloads/dow_jones_index/dow_jones_index.data"'

<b> Sample input file can be found @ https://github.com/skulkarni9/rbc-assignment/tree/main/src/test/resources </b>

2. curl --location --request GET 'localhost:8080/assignment/stockdata/AA'

3. curl --location --request POST 'localhost:8080/assignment/stockdata' \
--header 'Content-Type: application/json' \
--data-raw '{
    "quarter": 2,
    "stock": "AA",
    "date": "1/7/2011",
    "open": "$15.82",
    "high": "$16.72",
    "low": "$15.78",
    "close": "$16.42",
    "volume": 99423717,
    "percentChangePrice": 3.82,
    "percentChangeVolumeOverLastWk": -10.65,
    "previousWeeksVolume": 111273573,
    "nextWeeksOpen": "$16.7",
    "nextWeeksClose": "$15.97",
    "percentChangeNextWeeksPrice": 7.16,
    "daysToNextDividend": 40,
    "percentReturnNextDividend": 0.20
}'
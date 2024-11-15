/*
package com.exchangepoint.model;

import java.util.HashMap;
import java.util.Map;

*/
/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 * Author: Bogdan Fesenko
 * Date: 15-11-2024
 *//*

*/
/*

 *//*

public class Db {

    private Map<String, Rate> rateMap;

    public Db() {
        this.rateMap = initRateMap();

    }

    private Map<String,Rate> initRateMap() {
        Map<String,Rate> map = new HashMap<>();
        // TODO заполнить рейты валюты
        return map;
     }


}
*/
package com.exchangepoint.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 * Author: Bogdan Fesenko
 * Date: 15-11-2024
 */
public class Db {

    private Map<String, Rate> rateMap;

    public Db() {
        this.rateMap = initRateMap();
    }

    private Map<String, Rate> initRateMap() {
        Map<String, Rate> map = new HashMap<>();

        // Курсы валют актуальны на определенную дату
        // Обязательно обновите курсы до актуальных перед использованием

        // Кросс-курсы между фиатными валютами
        map.put("EUR-USD", new Rate(1.10));
        map.put("EUR-UAH", new Rate(40.00));
        map.put("USD-EUR", new Rate(0.91));
        map.put("USD-UAH", new Rate(36.50));
        map.put("UAH-EUR", new Rate(0.025));
        map.put("UAH-USD", new Rate(0.0274));

        // Курсы между фиатными валютами и криптовалютами
        map.put("EUR-BTC", new Rate(0.000025));
        map.put("BTC-EUR", new Rate(40000.0));
        map.put("EUR-ETH", new Rate(0.00035));
        map.put("ETH-EUR", new Rate(2850.0));

        map.put("USD-BTC", new Rate(0.000023));
        map.put("BTC-USD", new Rate(43000.0));
        map.put("USD-ETH", new Rate(0.00032));
        map.put("ETH-USD", new Rate(3125.0));

        map.put("UAH-BTC", new Rate(0.0000006));
        map.put("BTC-UAH", new Rate(1600000.0));
        map.put("UAH-ETH", new Rate(0.000008));
        map.put("ETH-UAH", new Rate(125000.0));

        // Курсы между криптовалютами
        map.put("BTC-ETH", new Rate(14.0));
        map.put("ETH-BTC", new Rate(0.0714));

        return map;
    }

    // Дополнительные методы для доступа к rateMap
    public Rate getRate(String currencyPair) {
        return rateMap.get(currencyPair);
    }

    public void setRate(String currencyPair, Rate rate) {
        rateMap.put(currencyPair, rate);
    }

    public Map<String, Rate> getAllRates() {
        return rateMap;
    }
}

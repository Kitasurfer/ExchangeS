package com.exchangepoint.model;

import java.time.LocalDateTime;

/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 * Author: Bogdan Fesenko
 * Date: 15-11-2024
 */
/*

 */


        public class Rate {
            private double rate;                // Курс обмена
            private LocalDateTime created;    // Время установки курса

            public Rate(double rate) {
                this.rate = rate;
                this.created = LocalDateTime.now();
            }

            // Геттеры и сеттеры
            public double getRate() {
                return rate;
            }

            public void setRate(double rate) {
                this.rate = rate;
            }

            public LocalDateTime getTimestamp() {
                return created;
            }

            public void setTimestamp(LocalDateTime timestamp) {
                this.created = timestamp;
            }
        }


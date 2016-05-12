package com.android.HuoBiAssistant.model.bean;

import java.io.Serializable;

/**
 * Created by apple on 16/4/4.
 */
public class BTCBean implements Serializable {
    private String time;
    private Ticker ticker;

    @Override
    public String toString() {
        return "BTCBean{" +
                "time='" + time + '\'' +
                ", ticker=" + ticker +
                '}';
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Ticker getTicker() {
        return ticker;
    }

    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }

    public class Ticker implements Serializable {
        private double open;
        private double vol;
        private String symbol;
        private double last;
        private double buy;
        private double sell;
        private double high;
        private double low;

        @Override
        public String toString() {
            return "Ticker{" +
                    "open=" + open +
                    ", vol=" + vol +
                    ", symbol='" + symbol + '\'' +
                    ", last=" + last +
                    ", buy=" + buy +
                    ", sell=" + sell +
                    ", high=" + high +
                    ", low=" + low +
                    '}';
        }

        public double getBuy() {
            return buy;
        }

        public void setBuy(double buy) {
            this.buy = buy;
        }

        public double getHigh() {
            return high;
        }

        public void setHigh(double high) {
            this.high = high;
        }

        public double getLast() {
            return last;
        }

        public void setLast(double last) {
            this.last = last;
        }

        public double getLow() {
            return low;
        }

        public void setLow(double low) {
            this.low = low;
        }

        public double getOpen() {
            return open;
        }

        public void setOpen(double open) {
            this.open = open;
        }

        public double getSell() {
            return sell;
        }

        public void setSell(double sell) {
            this.sell = sell;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public double getVol() {
            return vol;
        }

        public void setVol(double vol) {
            this.vol = vol;
        }
    }
}

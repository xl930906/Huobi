package com.android.HuoBiAssistant.model.bean;

import com.android.HuoBiAssistant.model.ParamName;

/**
 * Created by Dragon丶Lz on 2016/4/3.
 */
public class AccountInfo {

    @ParamName("total")
    private String total;

    @ParamName("net_asset")
    private String  net_asset;


    @ParamName("available_cny_display")
    private String  available_cny_display;


    @ParamName("available_btc_display")
    private String available_btc_display;

    @ParamName("frozen_cny_display")
    private String frozen_cny_display;

    @ParamName("frozen_btc_display")
    private String frozen_btc_display;

    @ParamName("loan_cny_display")
    private String loan_cny_display;


    @ParamName("loan_btc_display")
    private String loan_btc_display;

 public String getTotal() {
  return total;
 }

 public void setTotal(String total) {
  this.total = total;
 }

 public String getNet_asset() {
  return net_asset;
 }

 public void setNet_asset(String net_asset) {
  this.net_asset = net_asset;
 }

 public String getAvailable_cny_display() {
  return available_cny_display;
 }

 public void setAvailable_cny_display(String available_cny_display) {
  this.available_cny_display = available_cny_display;
 }

 public String getAvailable_btc_display() {
  return available_btc_display;
 }

 public void setAvailable_btc_display(String available_btc_display) {
  this.available_btc_display = available_btc_display;
 }

 public String getFrozen_cny_display() {
  return frozen_cny_display;
 }

 public void setFrozen_cny_display(String frozen_cny_display) {
  this.frozen_cny_display = frozen_cny_display;
 }

 public String getFrozen_btc_display() {
  return frozen_btc_display;
 }

 public void setFrozen_btc_display(String frozen_btc_display) {
  this.frozen_btc_display = frozen_btc_display;
 }

 public String getLoan_cny_display() {
  return loan_cny_display;
 }

 public void setLoan_cny_display(String loan_cny_display) {
  this.loan_cny_display = loan_cny_display;
 }

 public String getLoan_btc_display() {
  return loan_btc_display;
 }

 public void setLoan_btc_display(String loan_btc_display) {
  this.loan_btc_display = loan_btc_display;
 }
}

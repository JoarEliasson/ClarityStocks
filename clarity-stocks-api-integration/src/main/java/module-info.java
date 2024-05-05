module clarity.stocks.api.integration {
  
  requires clarity.stocks.common;
  requires java.net.http;
  requires com.fasterxml.jackson.databind;

  exports alphaVantage;

}
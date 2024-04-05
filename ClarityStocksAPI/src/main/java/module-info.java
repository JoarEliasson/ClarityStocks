/**
 * Descriptor file. Specifies requirements and exports of module ClarityStocksAPI. Declares
 * dependencies for this module. Descrines accessible packages to other modules. requires - means
 * this module requires a certain module exports - certain package is exported by this module
 */

module ClarityStocksAPI {
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires ClarityStocksAnalysis;

  exports alphaVantage;
}
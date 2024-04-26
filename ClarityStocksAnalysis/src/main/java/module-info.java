module ClarityStocksAnalysis {
    requires ClarityStocksAPI;
    requires commons.math3;
    requires org.jfree.jfreechart;
    exports analysis.model.unfinished;
  exports analysis.model.evaluations;
}
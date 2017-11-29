# The following URL provides one year historical stock price quotes:

def pricesURL(ticker: String): String = s” https://finance.google.com/finance/historical?q=NASDAQ:AMZN&output=csv%E2%80%9D”

Task
Please write Scala functions that will return, feel free to adapt function signatures, these are just indicative. Use Close price everywhere:

/* 1 - 1 year historic prices given a ticker */
def dailyPrices(ticker: String) : List[Double]

/* 2- daily returns, where return = ( Price_Today – Price_Yesterday)/Price_Yesterday */
def returns(ticker:String) : Seq[Double]

/* 3 – 1 year mean returns */
def meanReturn(ticker:String): Double

/* example usage */
val googleDailyPrices = dailyPrices(“GOOG”)
val googleDailyReturns = returns(“GOOG”)
val googleAverageReturns = meanReturn(“GOOG”)



## To Run

Just run `sbt run` and the application will run for the GOOG ticker

To run the tests `sbt test` will run the unit tests
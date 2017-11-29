package io.github.arausuy.tracker

import io.github.arausuy.model.DailyPrice

class Tracker(dailyPrices: List[DailyPrice]) {

  private val orderedDailies = dailyPrices.sortWith((d1, d2) => d1.date.isBefore(d2.date))
  def prices(): List[BigDecimal] = dailyPrices.map(_.close)

  def dailyReturns(): List[BigDecimal] = {

    orderedDailies.sliding(2).map {
      case y :: t :: Nil => (t.close - y.close) / y.close
    }.toList
  }

  def meanReturns(): BigDecimal = {
    val dR = dailyReturns()
    dR.sum / dR.size
  }
}

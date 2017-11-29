package io.github.arausuy.tracker

import java.time.LocalDate

import io.github.arausuy.model.DailyPrice
import org.scalatest.{Matchers, WordSpecLike}


class TrackerSpec extends WordSpecLike with Matchers {


  val dailyPrices = List(
    DailyPrice(LocalDate.now(), BigDecimal(1), BigDecimal(2), BigDecimal(1), BigDecimal(2), 123),
    DailyPrice(LocalDate.now().minusDays(1), BigDecimal(1), BigDecimal(10), BigDecimal(1), BigDecimal(10), 123),
    DailyPrice(LocalDate.now().minusDays(2), BigDecimal(1), BigDecimal(5), BigDecimal(1), BigDecimal(5), 123)
  )

  "Tracker " should {
    "output a set of list of closing prices" in {
      val tracker = new Tracker(dailyPrices)
      tracker.prices() shouldBe List(BigDecimal(2), BigDecimal(10), BigDecimal(5))
    }

    "output an empty list of closing prices" in {
      val tracker = new Tracker(List.empty[DailyPrice])
      tracker.prices() shouldBe List.empty[DailyPrice]
    }

    "get a list of dailyprices" in {
      val tracker = new Tracker(dailyPrices)
      tracker.dailyReturns().size shouldBe 2
    }

    "output an empty list of dailyprices" in {
      val tracker = new Tracker(List.empty[DailyPrice])
      tracker.dailyReturns() shouldBe List.empty[DailyPrice]
    }

    "output the mean dailyprice" in {
      val tracker = new Tracker(dailyPrices)
      tracker.meanReturns() shouldBe BigDecimal(0.1)
    }


  }
}

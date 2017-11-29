package io.github.arausuy

import com.typesafe.scalalogging.LazyLogging
import io.github.arausuy.http.HttpConnector
import io.github.arausuy.parser.CSVParser
import io.github.arausuy.tracker.Tracker

import scala.concurrent.ExecutionContext.Implicits.global

object Application extends App with LazyLogging {
  val httpClient = new HttpConnector()
  val csvParser = new CSVParser()
  //TODO: use argsv to populate argument
  val res = httpClient.getCSVForTicket("GOOG")

  res.map {
    case Right(r) =>
      csvParser.parseCSV(r) match {
      case Right(r) =>
        val tracker = new Tracker(r)
        logger.info(s"Prices: ${tracker.prices()}")
        logger.info(s"Daily Returns: ${tracker.dailyReturns()}")
        logger.info(s"Mean Returns: ${tracker.meanReturns()}")
      case Left(l) => l.foreach(e => logger.info(e.getMessage, e))
    }
    case Left(l) => logger.info(l)
  }
}

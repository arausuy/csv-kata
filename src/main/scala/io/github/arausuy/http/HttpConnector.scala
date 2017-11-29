package io.github.arausuy.http

import dispatch._
import Defaults._


class HttpConnector() {

  def getCSVForTicket(ticket: String): Future[Either[String, String]] = {
    //TODO: Config out the url
    val urlForCSV = url(s"https://finance.google.com/finance/historical?q=NASDAQ:${ticket.toUpperCase()}&output=csv")
    val res = Http.default(urlForCSV OK as.String)

    res
      .map(Right(_))
      .recover {
        case e => Left(e.getMessage)
      }

  }

}

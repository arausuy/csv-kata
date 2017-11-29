package io.github.arausuy.parser

import java.time.LocalDate

import io.github.arausuy.model.DailyPrice
import java.time.format.DateTimeFormatter

import scala.util.Try

class CSVParser {

  private val sep = "\n"
  private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("d-MMM-yy")

  def parseCSV(csv: String): Either[List[Throwable], List[DailyPrice]] = {
    val parseAttempt = csv.split(sep).toList.drop(1).map(_.split(",")).map(parseLineToDailyPrice)
    if (!parseAttempt.exists(_.isLeft)) {
      Right(parseAttempt.filter(_.isRight).collect { case Right(r) => r })
    } else {
      Left(parseAttempt.filter(_.isLeft).collect { case Left(l) => l })
    }
  }

  private def parseLineToDailyPrice(line: Array[String]): Either[Throwable, DailyPrice] = {
    if (line.length == 6) {
      Try {
        val date = LocalDate.parse(line(0), formatter)
        val open = BigDecimal(line(1))
        val low = BigDecimal(line(2))
        val high = BigDecimal(line(3))
        val close = BigDecimal(line(4))
        val volume = line(5).toInt
        DailyPrice(date, open, low, high, close, volume)
      }.toEither.fold(
        e => Left(ParserException(e)),
        dp => Right(dp)
      )
    } else {
      Left(ParserException(s"Failed to parse csv line - length of the line is too short $sep ${line.mkString(",")}"))
    }
  }

}

case class ParserException(message: String, e: Throwable) extends Exception(message, e)

object ParserException {
  def apply(e: Throwable): ParserException = {
    new ParserException(e.getMessage, e)
  }

  def apply(str: String): ParserException = {
    new ParserException(str, new IllegalArgumentException(str))
  }
}

package io.github.arausuy.parser

import io.github.arausuy.model.DailyPrice
import org.scalatest.{Matchers, WordSpecLike}
import scala.compat.Platform.EOL

class CSVParserSpec extends WordSpecLike with Matchers {


  val csv = "Date,Open,High,Low,Close,Volume\n13-Sep-17,930.66,937.25,929.86,935.09,1102631\n12-Sep-17,932.59,933.48,923.86,932.07,1134397\n11-Sep-17,934.25,938.38,926.92,929.08,1266991"


  val badDatecsv = "Date,Open,High,Low,Close,Volume\n13-ZtZ-17,930.66,937.25,929.86,935.09,1102631\n12-Sep-17,932.59,933.48,923.86,932.07,1134397\n11-Sep-17,934.25,938.38,926.92,929.08,1266991"

  val malformedCsv = "Date,Open,High,Low,Close,Volume\n13-Sep-17,930.66937.25,929.86,935.09,1102631\n12-Sep-17,932.59,933.48,923.86,932.07,1134397\n11-Sep-17,934.25,938.38,926.92,929.08,1266991"

  val csvP = new CSVParser()

  "CSV Parser" should {
    "parse a well formed csv" in {
      val parsed: Either[List[Throwable], List[DailyPrice]] = csvP.parseCSV(csv)

      parsed.isRight shouldBe true
      parsed.right.get.size shouldBe 3
    }
    "inform you of a malformed csv" in {
      val parsed: Either[List[Throwable], List[DailyPrice]] = csvP.parseCSV(malformedCsv)

      parsed.isLeft shouldBe true
      parsed.left.get.size shouldBe 1
      parsed.left.get.head shouldBe a[ParserException]
      parsed.left.get.head.getMessage should include("line")
    }


    "inform you of inability to parse date format" in {
      val parsed: Either[List[Throwable], List[DailyPrice]] = csvP.parseCSV(badDatecsv)

      parsed.isLeft shouldBe true
      parsed.left.get.size shouldBe 1
      parsed.left.get.head shouldBe a[ParserException]
      parsed.left.get.head.getMessage should include("13-ZtZ-17")
    }
  }
}

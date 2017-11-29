package io.github.arausuy.model

import java.time.LocalDate


case class DailyPrice(date: LocalDate,
                      open: BigDecimal,
                      high: BigDecimal,
                      low: BigDecimal,
                      close: BigDecimal,
                      volume: Int)

package com.example.myplayground

import kotlin.math.min

fun main() {

    val prices1 = listOf(200, 500, 1000, 700, 30, 400, 900, 400, 50)
//    buyAndSellStock2(prices1)
//    stock(prices1)

//    val prices2 = listOf(100)
//    buyAndSellStock2(prices2)

    val ice = arrayOf(1, 3, 4, 5)
    val ice2 = arrayOf(1 ,4 ,5 ,3, 2)
    val result = iceCream(ice2)
    println(result.joinToString("-"))

//    index 0 -> 4-1 = 3
//    map[1] = 1
//
//    index 1 -> 4-3 = 1
//    map[1] = 2
//
//    index 2 -> 4-4 = 0
//    map[0] = 3
//
//    index 3 -> 4-5 = -1
//    map[-1] = 4
}

fun iceCream(costs: Array<Int>): Array<Int> {
    val m = 4
    val priceMap = HashMap<Int, Int>()
    val priceList = mutableListOf<Int>()

    costs.forEachIndexed { i, v ->
        val key = m - v
        if (priceMap.containsKey(key)) {
            priceList.add(priceMap[key]!!)
            priceList.add(i+1)
            return priceList.toTypedArray()
        }
        priceMap[v] = i + 1
    }

    return priceList.toTypedArray()
}

fun buyAndSellStock1() {
    // Input --
    val prices = listOf(200, 500, 1000, 700, 30, 400, 900, 400, 50)
    var max = 0
    var indexBuy = 0
    var indexSell = 0

    // Process --
    for (i in prices.indices) {
        for (k in i+1 until prices.size) {
            val temp = prices[k] - prices[i]
            if (max < temp) {
                max = temp
                indexBuy = i
                indexSell = k
            }
        }
    }

    // Output --
    println("Maximum profit: $max")
    println("Sell on Index: $indexSell")
    println("Buy on Index: $indexBuy")
}

fun stock(prices: List<Int>) {

    var minPrice = prices[0]
    var buy = 0
    var sell = 0
    var maxProfit = 0

    for (i in prices.indices) {

        val currentPrice = prices[i]
        if (currentPrice < minPrice ) {
            minPrice = currentPrice
            buy = i
        }

        val potentialProfit = currentPrice - minPrice
        if (potentialProfit > maxProfit) {
            maxProfit = potentialProfit
            sell = i
        }
    }

    println(buy)
    println(sell)
}

fun buyAndSellStock2(prices: List<Int>) {
    val size = prices.size
    var minPrice = prices[0]
    var indexBuy = 0
    var indexSell = 0
    var maxProfit = 0

    if (size < 2) {
        println("No transaction can be made")
        return
    }

    for (i in prices.indices) {
        val currentPrice = prices[i]
        if (currentPrice < minPrice) {
            minPrice = currentPrice
            indexBuy = i
        }

        val potentialProfit = currentPrice - minPrice
        if (potentialProfit > maxProfit) {
            maxProfit = potentialProfit
            indexSell = i
        }
    }

    println("Maximum profit: $maxProfit")
    println("Sell on Index: $indexSell -> ${prices[indexSell]}")
    println("Buy on Index: $indexBuy -> ${prices[indexBuy]}")
}
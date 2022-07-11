package com.example.sieve


import java.lang.Exception
import java.math.BigDecimal
import java.util.*
import kotlin.Throws
import kotlin.jvm.JvmStatic
import kotlin.math.pow
import kotlin.math.sqrt

object SieveOfEratosthenes {
    // boolean型配列の初期値はfalse
    private var proveNumbers = BooleanArray(0) // 素数ならばtrue、それ以外にはfalseが入る
    private fun eratosthenes(): Long {
        proveNumbers[2] = true
        var i = 3
        while (i < proveNumbers.size) {
            if (primeCheck(i)) {
                proveNumbers[i] = true // 素数ならばtrue
            }
            i += 2
        }
        return add() // 素数の和
    }

    private fun primeCheck(n: Int): Boolean {
        for (i in 2..sqrt(n.toDouble()).toInt()) { // 篩い落とし作業は、入力された数字の平方根に達するまで続ける
            if (proveNumbers[i] && n % i == 0) {
                return false
            }
        }
        return true
    }

    private fun add(): Long { // numbers配列のtrueになっている要素番号(素数)を足している
        var sum: Long = 2
        var i = 3
        while (i < proveNumbers.size) {
            if (proveNumbers[i]) {
                sum += i.toLong()
            }
            i += 2
        }
        return sum
    }

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        println("このプログラムは2~200万以下の全ての素数の和を求めます")
        var loop = true
        while (loop) { // 2~200万の整数が出るまで繰返し
            try {
                println("2～200万までの整数を入力してください")
                val n1 = Scanner(System.`in`)
                val num1 = n1.nextInt() // 整数入力
                if (num1 < 2 || num1 > 2000000) {
                    println("範囲外なのでもう一度やり直してください")
                } else {
                    proveNumbers = BooleanArray(num1 + 1) // +1しないと範囲外になる場合がある 例：2など
                    val startTime = System.nanoTime() // 計測開始
                    val test = eratosthenes()
                    val endTime = System.nanoTime() // 計測終了
                    println("2～200万までの整数で、" + num1 + "以下の素数の和=" + test)
//                    println("-----処理速度結果(指数表記ナシver)-----")
                    println("-----処理速度結果-----")
                    println(
                        BigDecimal.valueOf(endTime - startTime).toString() + "ナノ秒"
                    ) // 処理速度結果(ナノ秒)
                    println(
                        BigDecimal.valueOf((endTime - startTime) / 10.0.pow(6.0))
                            .toString() + "ミリ秒"
                    ) // 処理速度結果(ミリ秒)
                    println(
                        BigDecimal.valueOf((endTime - startTime) / 10.0.pow(9.0))
                            .toString() + "秒"
                    ) // 処理速度結果(秒)
//                    println("-----処理速度結果(指数表記アリver)-----")
//                    println((endTime - startTime).toString() + "ナノ秒") // 処理速度結果(ナノ秒)
//                    println(
//                        "%e".format((endTime - startTime) / 10.0.pow(6.0)) + "ミリ秒"
//                    ) // 処理速度結果(ミリ秒)
//                    println(
//                        "%e".format((endTime - startTime) / 10.0.pow(9.0)) + "秒"
//                    ) // 処理速度結果(秒)
                    n1.close() // scannerを閉じる
                    loop = false // ループを抜ける
                }
            } catch (e: Throwable) {
                // TODO: handle exception
                val ste = e.stackTrace
                println(e.javaClass.name + ": " + e.message)
                println("エラー発生個所：" + ste[ste.size - 1]) // 最後の要素の例外発生場所のみ表示
                loop = false
            }
        }
    }
}

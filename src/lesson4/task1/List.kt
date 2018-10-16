@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson3.task1.minDivisor
import kotlin.math.*

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var sum = 0.0
    for (a in v) {
        sum += a * a
    }
    return sqrt(sum)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.isEmpty()) return 0.0
    var sum = 0.0
    for (element in list) {
        sum += element
    }
    return sum / list.size
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.isEmpty()) return list
    val x = mean(list)
    for (i in 0 until list.size) {
        list[i] -= x
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    if (a.isEmpty()) return 0.0
    var c = 0.0
    for (i in 0 until a.size) c += a[i] * b[i]
    return c
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    if (p.isEmpty()) return 0.0
    var px = 0.0
    var tempX = 1.0
    for (element in p) {
        px += element * tempX
        tempX *= x
    }
    return px
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    if (list.isEmpty()) return list
    for (i in 1 until list.size) {
        list[i] += list[i - 1]
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var tempNumber = n
    val result = mutableListOf<Int>()
    var tempDivisor = minDivisor(n)
    while (tempNumber != 1) {
        if (tempNumber % tempDivisor != 0) {
            tempDivisor = tempNumber
            for (i in 2..sqrt(tempNumber.toDouble()).toInt()) {
                if (tempNumber % i == 0) {
                    tempDivisor = i
                    break
                }
            }
        }
        result.add(tempDivisor)
        tempNumber /= tempDivisor
    }
    return result
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    if (base > n) return listOf(n)
    var tempNumber = n
    val result = mutableListOf<Int>()
    while (tempNumber != 0) {
        result.add(tempNumber % base)
        tempNumber /= base
    }
    return result.reversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
    if (base <= 10) return convert(n, base).joinToString(separator = "")
    val list = convert(n, base)
    return buildString {
        for (digit in list) {
            if (digit > 9) append((digit - 10 + 'a'.toInt()).toChar()) else append(digit)
        }
    }
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var result = 0
    var tempDigit = 1
    for (digit in digits.reversed()) {
        result += digit * tempDigit
        tempDigit *= base
    }
    return result
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int {
    val list = mutableListOf<Int>()
    for (char in str) {
        if (char.toInt() in 'a'.toInt()..'z'.toInt()) list.add(char.toInt() - 'a'.toInt() + 10)
        else list.add(char.toInt() - '0'.toInt())
    }
    return decimal(list, base)
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var tempN = n

    val numbers = listOf(1000 to "M", 900 to "CM", 500 to "D", 400 to "CD", 100 to "C", 90 to "XC", 50 to "L", 40 to "XL", 10 to "X",
            9 to "IX", 5 to "V", 4 to "IV", 1 to "I")
    return buildString {
        while (tempN > 0) {
            for (i in 0 until numbers.size) {
                while (tempN >= (numbers[i].first)) {
                    tempN -= numbers[i].first
                    append(numbers[i].second)
                }
            }
        }
    }
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var tempN = n
    val listN = mutableListOf(0, 0, 0, 0, 0, 0)
    var i = 0
    while (tempN > 0) {
        listN[i] = (tempN % 10)
        i++
        tempN = (tempN - tempN % 10) / 10
    }
    var result = ""
    var count: Boolean
    var t = 0
    if (n >= 1000) t = 1
    for (j in t downTo 0) {
        count = true
        result += when (listN[j * 3 + 2]) {
            1 -> "сто "
            2 -> "двести "
            3 -> "триста "
            4 -> "четыреста "
            5 -> "пятьсот "
            6 -> "шестьсот "
            7 -> "семьсот "
            8 -> "восемьсот "
            9 -> "девятьсот "
            else -> ""
        }
        when (listN[j * 3 + 1]) {
            1 -> {
                result += when (listN[3 * j]) {
                    0 -> "десять "
                    1 -> "одиннадцать "
                    2 -> "двенадцать "
                    3 -> "тринадцать "
                    4 -> "четырнадцать "
                    5 -> "пятнадцать "
                    6 -> "шестнадцать "
                    7 -> "семнадцать "
                    8 -> "восемнадцать "
                    else -> "девятнадцать "
                }
                count = false
            }
            2 -> result += "двадцать "
            3 -> result += "тридцать "
            4 -> result += "сорок "
            5 -> result += "пятьдесят "
            6 -> result += "шестьдесят "
            7 -> result += "семьдесят "
            8 -> result += "восемьдесят "
            9 -> result += "девяносто "
            else -> result += ""
        }
        if (count) {
            when (listN[3 * j]) {
                1 -> result += if (j == 1) "одна тысяча " else "один"
                2 -> result += if (j == 1) "две тысячи " else "два"
                3 -> {
                    result += "три "
                    if (j == 1) result += "тысячи "
                }
                4 -> {
                    result += "четыре "
                    if (j == 1) result += "тысячи "
                }
                5 -> {
                    result += "пять "
                    if (j == 1) result += "тысяч "
                }
                6 -> {
                    result += "шесть "
                    if (j == 1) result += "тысяч "
                }
                7 -> {
                    result += "семь "
                    if (j == 1) result += "тысяч "
                }
                8 -> {
                    result += "восемь "
                    if (j == 1) result += "тысяч "
                }
                9 -> {
                    result += "девять "
                    if (j == 1) result += "тысяч "
                }
                else -> if (j == 1) result += "тысяч "
            }
        } else if (j == 1) result += "тысяч "
    }
    return result.trim()
}
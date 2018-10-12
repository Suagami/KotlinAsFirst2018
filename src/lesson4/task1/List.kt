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
    for (i in 0 until v.size) {
        sum += v[i] * v[i]
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
    for (i in 0 until list.size) {
        sum += list[i]
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
    for (i in 0 until p.size) {
        px += p[i] * tempX
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
    while (tempNumber != 1) {
        result.add(minDivisor(tempNumber))
        tempNumber /= result.last()
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
    var tempBase = 1.0
    var count = 0
    while (tempBase < n) {
        tempBase *= base
        count++
    }
    for (i in 1 until count) {
        tempBase /= base
        result.add(floor(tempNumber / tempBase).toInt())
        tempNumber %= tempBase.toInt()
    }
    result.add(n % base)
    return result
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
    val letters = listOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')
    val array = convert(n, base).toTypedArray()
    var result = ""
    for (i in 0 until array.size) {
        if (array[i] > 9) result += letters[array[i] - 10] else result += array[i]
    }
    return result
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
    var maxDigit = (base.toDouble().pow((digits.size - 1).toDouble())).toInt()
    for (i in 0 until digits.size) {
        result += digits[i] * maxDigit
        maxDigit /= base
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
    var result = 0
    val letters = listOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')
    var maxDigit = (base.toDouble().pow((str.length - 1).toDouble())).toInt()
    for (char in str) {
        if (char in letters) result += (char.toInt() - 'a'.toInt() + 10) * maxDigit else result += (char.toInt() - '0'.toInt()) * maxDigit
        maxDigit /= base
    }
    return result
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
    var result = ""
    val numbers = listOf(1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000)
    val romanNumbers = listOf("I", "IV", "V", "IX", "X", "XL", "L",
            "XC", "C", "CD", "D", "CM", "M")
    while (tempN > 0) {
        for (i in numbers.size - 1 downTo 0) {
            while (tempN >= numbers[i]) {
                tempN -= numbers[i]
                result += romanNumbers[i]
            }
        }
    }
    return result
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
    var count = true
    val n2 = listOf(listN[0], listN[1], listN[2])
    if (n >= 1000) {
        var count1 = true
        val n1 = mutableListOf(0, 0, 0)
        for (j in 3 until listN.size) n1[j - 3] = listN[j]
        when (n1[2]) {
            1 -> result += "сто "
            2 -> result += "двести "
            3 -> result += "триста "
            4 -> result += "четыреста "
            5 -> result += "пятьсот "
            6 -> result += "шестьсот "
            7 -> result += "семьсот "
            8 -> result += "восемьсот "
            9 -> result += "девятьсот "
            else -> result += ""
        }
        when (n1[1]) {
            1 -> {
                when (n1[0]) {
                    0 -> result += "десять тысяч "
                    1 -> result += "одиннадцать тысяч "
                    2 -> result += "двенадцать тысяч "
                    3 -> result += "тринадцать тысяч "
                    4 -> result += "четырнадцать тысяч "
                    5 -> result += "пятнадцать тысяч "
                    6 -> result += "шестнадцать тысяч "
                    7 -> result += "семнадцать тысяч "
                    8 -> result += "восемнадцать тысяч "
                    else -> result += "девятнадцать тысяч "
                }
                count1 = false
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
        if (count1) {
            when (n1[0]) {
                1 -> result += "одна тысяча "
                2 -> result += "две тысячи "
                3 -> result += "три тысячи "
                4 -> result += "четыре тысячи "
                5 -> result += "пять тысяч "
                6 -> result += "шесть тысяч "
                7 -> result += "семь тысяч "
                8 -> result += "восемь тысяч "
                9 -> result += "девять тысяч "
                else -> result += "тысяч "
            }

        }
    }
    when (n2[2]) {
        1 -> result += "сто "
        2 -> result += "двести "
        3 -> result += "триста "
        4 -> result += "четыреста "
        5 -> result += "пятьсот "
        6 -> result += "шестьсот "
        7 -> result += "семьсот "
        8 -> result += "восемьсот "
        9 -> result += "девятьсот "
        else -> result += ""
    }
    when (n2[1]) {
        1 -> {
            when (n2[0]) {
                0 -> result += "десять "
                1 -> result += "одиннадцать "
                2 -> result += "двенадцать "
                3 -> result += "тринадцать "
                4 -> result += "четырнадцать "
                5 -> result += "пятнадцать "
                6 -> result += "шестнадцать "
                7 -> result += "семнадцать "
                8 -> result += "восемнадцать "
                else -> result += "девятнадцать "
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
        when (n2[0]) {
            1 -> result += "один"
            2 -> result += "два"
            3 -> result += "три"
            4 -> result += "четыре"
            5 -> result += "пять"
            6 -> result += "шесть"
            7 -> result += "семь"
            8 -> result += "восемь"
            9 -> result += "девять"
            else -> result += ""
        }
    }
    result = result.trim()
    return result
}
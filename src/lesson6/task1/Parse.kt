@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth
import kotlin.math.*

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main(args: Array<String>) {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    val date = str.split(" ")
    val months = listOf("января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября",
            "октября", "ноября", "декабря")
    if (date.size != 3) return ""
    if (date[1] !in months) return ""
    val condition = (daysInMonth(months.indexOf(date[1]) + 1, date[2].toInt()) < date[0].toInt())
    if (condition) return ""
    val firstDate = if (date[0].toInt() in 0..9) "0${date[0].toInt()}" else date[0]
    val secondDate = if (months.indexOf(date[1]) in 0..8) "0${months.indexOf(date[1]) + 1}"
    else (months.indexOf(date[1]) + 1).toString()
    return "$firstDate.$secondDate.${date[2]}"
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val date = digital.split(".")
    val months = listOf("января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября",
            "октября", "ноября", "декабря")
    try {
        if (date.size != 3 || date[1].toInt() !in 1..12 || date[2].toInt() < 0) return ""
    } catch (e: NumberFormatException) {
        return ""
    }
    val condition = (daysInMonth(date[1].toInt(), date[2].toInt()) < date[0].toInt())
    if (condition) return ""
    return "${date[0].toInt()} ${months[date[1].toInt() - 1]} ${date[2]}"
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -98 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку
 */
fun flattenPhoneNumber(phone: String): String {
    val legitSymbols = listOf('+', '-', '(', ')', ' ')
    val res = buildString {
        for (char in phone) {
            if (char !in legitSymbols) {
                if (char.isDigit()) append(char) else return ""
            }
        }
    }
    return if (phone.startsWith('+')) "+$res" else res
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    val list = jumps.split(" ")
    val realList = mutableListOf<String>()
    for (element in list) if (element != "") realList.add(element)
    val results = mutableListOf<Int>()
    for (element in realList) {
        if (element != "-" && element != "%") {
            for (digit in element) if (!digit.isDigit()) return -1
            results.add(element.toInt())
        }
    }
    if (results.isEmpty()) return -1
    return results.sorted().last()
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    val list = jumps.split(" ")
    val signs = listOf('+', '%', '-')
    val results = mutableListOf<Int>()
    if (list.size % 2 == 1) return -1
    for (i in 0 until list.size / 2) {
        for (digit in list[2 * i])
            if (!digit.isDigit()) return -1
        for (symbol in list[2 * i + 1])
            if (symbol !in signs) return -1
        if (list[2 * i + 1].last() == '+') results.add(list[2 * i].toInt())
    }
    if (results.isEmpty()) return -1
    return results.sorted().last()
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    if (expression == "") throw IllegalArgumentException()
    val list = expression.split(" ")
    val signs = listOf("+", "-")
    for (digit in list[0]) if (!digit.isDigit()) throw IllegalArgumentException()
    var res = list[0].toInt()
    if (list.size % 2 == 0) throw IllegalArgumentException()
    for (i in 1..list.size / 2) {
        for (digit in list[2 * i]) if (!digit.isDigit()) throw IllegalArgumentException()
        when {
            list[2 * i - 1] !in signs -> throw IllegalArgumentException()
            list[2 * i - 1] == "+" -> res += list[2 * i].toInt()
            else -> res -= list[2 * i].toInt()
        }
    }
    return res
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val list = str.split(" ")
    var curIndex = 0
    for (i in 0 until list.size - 1) {
        if (list[i].toLowerCase() == list[i + 1].toLowerCase()) return curIndex
        else curIndex += list[i].length + 1
    }
    return -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    val list = description.split(" ")
    if (list.size % 2 == 1) return ""
    if (list.size == 2) return list[0]
    val mapOfProducts = mutableMapOf<String, Double>()
    var tempCost: String
    var tempCostList: List<String>
    for (i in 0 until list.size / 2) {
        if (i != list.size / 2 - 1 && list[2 * i + 1].last() != ';') return ""
        tempCost =
                if (list[2 * i + 1].last() == ';') list[2 * i + 1].substring(0, list[2 * i + 1].length - 1)
                else list[2 * i + 1]
        for (symbol in tempCost) if (!symbol.isDigit() && symbol != '.') return ""
        tempCostList =
                if ('.' in tempCost) tempCost.split(".")
                else listOf(tempCost, "0")
        for (digit in tempCostList[0]) if (!digit.isDigit()) return ""
        for (digit in tempCostList[1]) if (!digit.isDigit()) return ""
        mapOfProducts[list[2 * i]] = tempCostList[0].toInt() +
                tempCostList[1].toInt() / 10.0.pow(tempCostList[1].length)
    }
    return findMostExpensiveStuff(mapOfProducts)
}

fun findMostExpensiveStuff(stuff: Map<String, Double>): String {
    var res = ""
    var tempCost = 0.0
    for ((name, cost) in stuff) {
        if (cost > tempCost) {
            tempCost = cost
            res = name
        }
    }
    return res
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int {
    if (roman == "") return -1
    val numbers = mapOf(
            'I' to 1,
            'V' to 5,
            'X' to 10,
            'L' to 50,
            'C' to 100,
            'D' to 500,
            'M' to 1000
    )
    val listOfNumbers = setOf('I', 'V', 'X', 'L', 'C', 'D', 'M')
    val specialNumbers = setOf('I', 'X', 'C', 'M')
    for (letter in roman) if (numbers[letter] == null) return -1
    //println("pass1")
    for (i in 0 until roman.length - 1) {
        if (roman[i] !in specialNumbers && listOfNumbers.indexOf(roman[i]) <= listOfNumbers.indexOf(roman[i + 1])) return -1
        //println("pass2 num $i, let ${roman[i]}, next let ${roman[i + 1]}")
        //println("numbers[roman[i + 1]] = ${numbers[roman[i + 1]]}, 10.0.pow(specialNumbers.indexOf(roman[i])) = ${10.0.pow(specialNumbers.indexOf(roman[i]) + 1)}")
        if (roman[i] in specialNumbers && (numbers[roman[i + 1]]!!.toInt() != 10.0.pow(specialNumbers.indexOf(roman[i]) + 1).toInt() &&
                        numbers[roman[i + 1]]!!.toInt() != (10.0.pow(specialNumbers.indexOf(roman[i]) + 1) / 2).toInt()) &&
                listOfNumbers.indexOf(roman[i]) < listOfNumbers.indexOf(roman[i + 1])) return -1
        // println("pass3 num $i")
    }
    var res = 0
    for (i in 0 until roman.length - 1) {
        if (listOfNumbers.indexOf(roman[i]) % 2 == 0 && roman[i] != 'M') {
            if (numbers[roman[i + 1]]!!.toInt() == 10.0.pow(specialNumbers.indexOf(roman[i]) + 1).toInt() ||
                    numbers[roman[i + 1]]!!.toInt() == (10.0.pow(specialNumbers.indexOf(roman[i]) + 1) / 2).toInt())
                res -= numbers[roman[i]]!!.toInt() else res += numbers[roman[i]]!!.toInt()
        } else if (numbers[roman[i]] != null) res += numbers[roman[i]]!!.toInt()
        // println("pass 4, num $i, let ${roman[i]}, next let ${roman[i + 1]}, cur res = $res")
    }
    return res + numbers[roman.last()]!!.toInt()
}

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
// */
//fun searchingPairOfBrackets(commands: String, tempBrackets: Int?, isItCheckingForException: Boolean): Pair<Int?, Int> {
//    if (isItCheckingForException) {
//        var tempBracket = tempBrackets
//        for (command in commands) {
//            if (command == '[') {
//                if (tempBracket == null) tempBracket = 1 else tempBracket += 1
//            }
//            if (command == ']') {
//                if (tempBracket == null || tempBracket == 0) throw IllegalArgumentException()
//                else tempBracket -= 1
//            }
//        }
//        return Pair(tempBracket, 0)
//    }
//    var tempBracket = tempBrackets
//    var bracketsLength = 0
//    for (command in commands) {
//        if (bracketsLength > 0) bracketsLength++
//        if (command == '[') {
//            if (tempBracket == null) {
//                tempBracket = 1
//                bracketsLength = 1
//            } else tempBracket += 1
//        }
//        if (command == ']') {
//            if (tempBracket != null) tempBracket -= 1
//        }
//        if (tempBracket == 0) return Pair(tempBracket, bracketsLength)
//    }
//    return Pair(tempBracket, bracketsLength)
//}
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO() //{
//    val legalCommands = setOf('[', ']', '<', '>', '+', '-', ' ')
//    for (command in commands) if (command !in legalCommands) throw IllegalArgumentException()
//    var tempBracket: Int? = null
//    tempBracket = searchingPairOfBrackets(commands, tempBracket, true).first
//    if (tempBracket != 0) throw IllegalArgumentException()
//    val res = mutableListOf<Int>()
//    for (i in 0 until cells) res.add(0)
//    var tempPosition = cells / 2
//    var i = 0
//    while (i != commands.length) {
//        if (commands[i] == '>' || commands[i] == '<') {
//            tempPosition += simpleCommands(commands[i], tempPosition, cells)
//            i++
//            continue
//        }
//        if (commands[i] == '+' || commands[i] == '-') {
//            res[tempPosition] += simpleCommands(commands[i], tempPosition, cells)
//            i++
//            continue
//        }
//        if (commands[i] == '[') notSimpleCommands(commands[i])
//    }
//    return res
//}
//fun notSimpleCommands(res: MutableList<Int>, i: Int, position: Int, commands: String, cells: Int) {
//    var j = i
//    var tempPosition = position
//
//    while (res[tempPosition] != 0) {
//        for (command in commands) {
//            if (commands[j] == '>' || commands[j] == '<') {
//                tempPosition += simpleCommands(commands[j], tempPosition, cells)
//                j++
//                continue
//            }
//            if (commands[i] == '+' || commands[i] == '-') {
//                res[tempPosition] += simpleCommands(commands[i], tempPosition, cells)
//                j++
//                continue
//            }
//            if (commands[i] == '[') notSimpleCommands()
//            if (commands[i] == ']')
//        }
//    }
//}
//fun simpleCommands(command: Char, tempPosition: Int, cells: Int): Int {
//    if (command == '>') {
//        if (tempPosition == cells - 1) throw IllegalStateException() else return 1
//    }
//    if (command == '<') {
//        if (tempPosition == 0) throw IllegalStateException() else return -1
//    }
//    if (command == '+') return 1
//    if (command == '-') return -1
//    return 0
//}



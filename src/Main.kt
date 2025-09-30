class fraction(var numerator: Int, var denominator: Int){
    fun read(){
        println("Numerator: ")
        var n = readln().toInt()
        while(n == 0){
            println("numerator cant be 0")
            n = readln().toInt()
        }
        println("denominator: ")
        var d = readln().toInt()
        while(d == 0){
            println("denominator cant be 0")
            d = readln().toInt()
        }
        numerator = n
        denominator = d
    }

    fun print(){
        println("$numerator / $denominator")
    }

    fun gcd(A : Int,B : Int) : Int {
        var a = A
        var b = B
        while(b != 0){
            var r = a % b
            a = b
            b = r
        }
        return kotlin.math.abs(a)
    }

    fun simplify() {
        val beforeNumerator = numerator
        numerator /= gcd(numerator, denominator)
        denominator /= gcd(beforeNumerator, denominator)
    }

    fun compare(f : fraction) : Int {
        if(this.numerator * f.denominator < this.denominator * f.numerator){
            return -1
        }else if(this.numerator * f.denominator > this.denominator * f.numerator){
            return 1
        }else{
            return 0
        }
    }

    fun sum(f : fraction) : fraction {
        var n = this.numerator * f.denominator + this.denominator * f.numerator
        var d = this.denominator * f.denominator
        val result = fraction(n, d)
        result.simplify()
        return result
    }
}



fun main() {
    println("number of fractions: ")
    val n = readln().toInt()
    val fractions = mutableListOf<fraction>()
    for(i in 1..n){
        println("faction $i:")
        var f = fraction(1, 1)
        f.read()
        fractions.add(f)
    }
    println("All fractions.")
    for(i in 0..n - 1){
        fractions[i].print()
    }
    println("All simplify fractions.")
    for(i in 0..n - 1){
        fractions[i].simplify()
        fractions[i].print()
    }
    println("Sum.")
    var sum = fractions[0]
    for(i in 1..n - 1){
        sum = sum.sum(fractions[i])
    }
    sum.print()
    println("Max.")
    var max = fractions[0]
    for (i in 1..n - 1){
        if(max.compare(fractions[i]) != 1){
            max = fractions[i]
        }
    }
    max.print()
    println("Sorted descending.")
    fractions.sortWith { a, b -> b.compare(a) }
    for(i in 0..n-1){
        fractions[i].print()
    }
}
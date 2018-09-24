package basic

class Memoization {

    static int fib(int n) {
        if (n < 2) {
            return 1
        }
        return fib(n - 1) + fib(n - 2)
    }

    static long timeLog(def fun) {
        long start = System.currentTimeMillis()
        fun()
        long end = System.currentTimeMillis()
        return end - start
    }

    static void main(String[] args) {
        def mFib = Memoization.&fib.memoize()

        for (i in 1..10) {
            println "${i} + ${fib(i)}"
        }
        println "time spent: ${timeLog({ mFib(40) })}"
        println "time spent: ${timeLog({ mFib(40) })}"
        println "time spent: ${timeLog({ mFib(40) })}"
    }

}

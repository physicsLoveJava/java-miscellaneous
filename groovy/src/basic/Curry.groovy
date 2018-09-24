package basic

class Curry {

    static void main(String[] args) {
        def area = { a, b, c -> a * b * c }

        def h = area.curry(5)
        def hw = area.curry(5, 10)

        println "${h(10, 10)}, ${hw(10)}"

        def compose = { f, g, x -> f(g(x)) }

        println "${compose({ it * 2 }, { it + 5 }, 10)}"
        println "${compose({ it + 5 }, { it * 2 }, 10)}"
    }

}

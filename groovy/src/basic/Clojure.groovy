package basic

class Clojure {

    static void main(String[] args) {
        [1, 2, 3].each { element ->
            print element
        }
        print ([1, 2, 3].collect {element ->
            return element * 2
        })

        def var = 5

        def clo = {element ->
            print "hello ${element} ${var}"
        }

        clo.call(5)
    }

}

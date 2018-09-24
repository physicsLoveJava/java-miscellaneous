package basic

class GenericObject {

    static void main(String[] args) {
        def exp = new Expando(
            say: {a ->
                print "$a"
            }
        )
        exp.say('a')
        exp.say 'a'
//        def generic = new GenericObject()
//        generic.say = {
//            println "generic say method"
//        }
//        generic.say()
    }

}

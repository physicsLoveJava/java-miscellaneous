package basic

class PersonIntercept implements GroovyInterceptable{

    def say() {
        println "say"
    }

    def invokeMethod(String name, args) {
        if(name == 'println') {
            System.out.metaClass.invokeMethod(System.out, "println", args)
            return
        }
        println "$name is calling"
        if(name == 'say') {
            println "say is being calling"
            PersonIntercept.metaClass.invokeMethod(this, 'say', args)
        }
    }

    static void main(String[] args) {
        def person = new PersonIntercept()
        person.say()
    }
}

package basic

import groovy.xml.MarkupBuilder

class XMLBuilder {

    static void main(String[] args) {
        def mb = new MarkupBuilder()
        mb.root {
            beans (id: 'a') {
                bean (id: 'aa', class: 'aClass') {
                    property('sss')
                }
                bean (id: 'bb', class: 'aClass')
            }
        }
    }

}

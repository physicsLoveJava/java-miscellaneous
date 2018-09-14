package basic

class FileUsage {

    static void main(String[] args) {
//        def file = new File("groovy/target/production/groovy/basic/FileUsage.class")
//        file.eachLine {line ->
//            println line
//        }
        listFile(new File("groovy"))
    }

    static void listFile(file) {
        file.eachFile {subFile ->
            if(subFile.isDirectory()) {
                listFile(subFile)
            }else {
                println subFile
            }
        }
    }
}

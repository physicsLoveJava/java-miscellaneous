package basic

class Library {

    def library = new HashMap()

    void add(author, book) {
        if(library.containsKey(author)) {
            library.get(author) << book
        }else {
            library.put(author, [book])
        }
    }

    static void main(String[] args) {
        def library = new Library()

        library.add("justin", "a")
        library.add("justin", "a")
        library.add("justin", "a")
        library.add("justin", "a")
        library.add("tom", "a")

        print "${library.library}"
    }

}

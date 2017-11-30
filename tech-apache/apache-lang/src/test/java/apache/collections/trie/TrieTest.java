package apache.collections.trie;

import org.apache.commons.collections4.trie.PatriciaTrie;
import org.junit.Test;

public class TrieTest {

    @Test
    public void testTrie() {
        PatriciaTrie<String> trie = new PatriciaTrie<>();
        trie.put("aa", "11");
        trie.put("ab", "12");
        trie.put("ac", "13");
        trie.put("ad", "16");
        trie.put("ada", "17");
        trie.put("adb", "18");

        System.out.println(trie.headMap("ab"));
        System.out.println(trie.prefixMap("ad"));
    }

}

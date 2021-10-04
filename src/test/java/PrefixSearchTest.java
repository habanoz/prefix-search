import java.util.List;

public class PrefixSearchTest {
    static class PrefixRange {
        private final String company;
        private final String pStart;
        private final String pEnd;

        public PrefixRange(String company, String pStart, String pEnd) {
            this.company = company;
            this.pStart = pStart;
            this.pEnd = pEnd;
        }
    }

    static class PrefixCache {
        PrefixCache(List<PrefixRange> rangeList) {
            TrieNode root = new TrieNode();
            for (PrefixRange prefixRange : rangeList) {
                insert(root, prefixRange.pStart, prefixRange.company);
                insert(root, prefixRange.pEnd, prefixRange.company);
            }
        }

        private void insert(TrieNode root, String prefix, String company) {
            TrieNode cur = root;

            for (char c : prefix.toCharArray()) {
                int idx = c - '0';
                if (cur.children[idx] == null) {
                    cur.children[idx] = new TrieNode();
                    cur.children[idx].company = company;
                }
                cur = cur.children[idx];

                if (!cur.company.equals(company)) {
                    cur.company = null;
                }
            }
        }

        private String search(TrieNode root, String code) {
            TrieNode cur = root;

            for (char c : code.toCharArray()) {
                int idx = c - '0';

                if (cur.children[idx] == null) {
                    return null;
                }

                cur = cur.children[idx];

                if (cur.company != null) {
                    return cur.company;
                }
            }

            return null;
        }
    }


    static class TrieNode {
        TrieNode[] children = new TrieNode[10];
        private String company;
    }
}

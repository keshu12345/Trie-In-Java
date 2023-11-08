package Trie;

import java.util.Objects;

public class Trie {

    public static class Node {
        Node[] children;
        boolean eow;

        public Node() {
            children = new Node[26];

            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
            eow = false;
        }
    }

    static Node root = new Node();

    public static void insert(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {  //O(Length of word)
            int idx = word.charAt(i) - 'a';
            if (curr.children[idx] == null) {
                curr.children[idx] = new Node();
            }
            if (i == word.length() - 1) {
                curr.children[idx].eow = true;
            }
            curr = curr.children[idx];
        }

    }

    public static boolean search(String key) {
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            int idx = key.charAt(i) - 'a';
            if (curr.children[idx] == null) {
                return false;
            }
            if (i == key.length() - 1 && !curr.children[idx].eow) {
                return false;
            }
            curr = curr.children[idx];
        }
        return true;
    }

    public static boolean wordBreak(String key) {
        if (key.isEmpty()) {
            return true;
        }
        for (int i = 1; i <= key.length(); i++) {
            String firstPart = key.substring(0, i);
            String secondPart = key.substring(i);

            if (search(firstPart) && wordBreak(secondPart)) {
                return true;
            }
        }
        return false;
    }

    public static boolean startWith(String prefix) {
        Node curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            int idx = prefix.charAt(i) - 'a';
            if (curr.children[idx] == null) {
                return false;
            }
            curr = curr.children[idx];

        }
        return true;
    }

    public static String ans = "";

    public static void longestWord(Node root, StringBuilder temp) {
        if (root == null) {
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null && root.children[i].eow) {
                temp.append((char) (i + 'a'));
                if (temp.length() > ans.length()) {
                    ans = temp.toString();
                }
                longestWord(root.children[i], temp);
                temp.deleteCharAt(temp.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        String[] words = {"the", "a", "there", "their", "any", "thee"};

        for (String word : words) {
            insert(word);
        }
        System.out.println("there -> " + search("there"));
        System.out.println("thor -> " + search("thor"));
        System.out.println("thee -> " + search("thee"));
        System.out.println("a -> " + search("a"));
        System.out.println("three -> " + search("three"));

         words = new String[]{"i", "like", "sam", "samsung", "mobile"};
        String key ="ilikesamsung";
        for (String word : words) {
            insert(word);
        }
        System.out.println("i -> " + search("i"));
        System.out.println("like -> " + search("like"));
        System.out.println("samsung -> " + search("samsung"));
        System.out.println("Word break-->:"+wordBreak(key));

        System.out.println(startWith("sam"));
        countUniqueSubString();
        String str = "ababa";

        for (int i = 0; i < str.length(); i++) {
            String suffix = str.substring(i);
            insert(suffix);
        }
        System.out.println(countNode(root));

         words = new String[]{"a", "banana", "app", "appl", "ap", "apple", "apply"};

        for (String word : words) {
            insert(word);
        }
        longestWord(root, new StringBuilder());
        System.out.println(ans);

    }

    public static int countNode(Node root) {
        if (root == null) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null) {
                count += countNode(root.children[i]);
            }

        }
        return count + 1;
    }

    private static void countUniqueSubString() {
        String str = "ababa";

        for (int i = 0; i < str.length(); i++) {
            String suffix = str.substring(i);
            insert(suffix);
        }
        System.out.println(countNode(root));
    }
}

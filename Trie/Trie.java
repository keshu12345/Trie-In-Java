package Trie;

public class Trie {

    public static class Node{
       Node []children;
       boolean eow;

       public Node(){
           children=new Node[26];

           for(int i=0;i<26;i++){
               children[i]=null;
           }
          eow=false;
       }
    }

   static Node root=new Node();
    public static void insert(String word){
        Node curr=root;
        for(int i=0;i<word.length();i++){  //O(Length of word)
            int idx=word.charAt(i)-'a';
            if(curr.children[idx]==null){
                curr.children[idx]=new Node();
            }
            if(i==word.length()-1){
                curr.children[idx].eow=true;
            }
            curr=curr.children[idx];
        }

    }
    public static boolean search(String key){
        Node curr=root;
        for(int i=0;i<key.length();i++){
            int idx=key.charAt(i)-'a';
             Node node=curr.children[idx];
            if(node==null){
                return false;
            }
            if(i==key.length()-1&& !node.eow){
                return  false;
            }
            curr=node;
        }
        return true;
    }

    public static void main(String []args){
        String[] words = {"the", "a", "there", "their", "any", "thee"};

        for(String word:words){
            insert(word);
        }
        System.out.println("there -> " + search("there"));
        System.out.println("thor -> " + search("thor"));
        System.out.println("thee -> " + search("thee"));
        System.out.println("a -> " + search("a"));
        System.out.println("three -> " + search("three"));

    }
}

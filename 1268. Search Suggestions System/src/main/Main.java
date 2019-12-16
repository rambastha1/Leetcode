package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        if (searchWord == null || searchWord.length() < 2) {
            return Collections.emptyList();
        }

        TrieNode root = buildTrie(products, 3);

        List<List<String>> result = new ArrayList<>(); // Time: O(LCQ^2), Space: O(N * L * LCQ)
        for (int i = 0; i < searchWord.length(); i++) {
            result.add(getTopKProducts(root, searchWord.substring(0, i + 1), 3));
        }

        return result;   
    }
    
    private List<String> getTopKProducts(TrieNode root, String prefix, int k) {
        List<String> products = new LinkedList<>();

        TrieNode parent = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = Character.toLowerCase(prefix.charAt(i)); // comparison is case-insensitive

            TrieNode child = parent.children.get(c);
            if (child == null) { // null if 'prefix' is way too long or 'prefix' doesn't appear in the Trie
                return Collections.emptyList();
            }

            parent = child;
        }

        Queue<String> topProducts = parent.topProducts;
        for (int i = 0; !topProducts.isEmpty() && i < k; i++) {
            products.add(0, topProducts.poll());
        }

        for (String product : products) { // Put the top k products back to the pq
            parent.topProducts.offer(product);
        }

        return products;
    }

    private TrieNode buildTrie(String[] repository, int k) { // Time: O(N * L), Space: O(N * L * L)
        TrieNode root = new TrieNode();
        if (repository == null || repository.length == 0) {
            return root;
        }

        for (String product : repository) { // Build Trie using the repository
            if (product == null) {
                continue;
            }

            TrieNode parent = root;
            for (int i = 0; i < product.length(); i++) {
                char c = Character.toLowerCase(product.charAt(i)); 

                TrieNode child = parent.children.get(c);
                if (child == null) {
                    child = new TrieNode();
                    parent.children.put(c, child);
                }

                child.topProducts.offer(product); 
                if (child.topProducts.size() > k) { // keep the size of pq small
                    child.topProducts.poll();
                }

                parent = child; // Navigate to next level
            }
        }

        return root;
    }

    private class TrieNode {
        Map<Character, TrieNode> children;
        PriorityQueue<String> topProducts;

        TrieNode() {
            children = new HashMap<>();
            topProducts = new PriorityQueue<>((s1, s2) -> s2.toLowerCase().compareTo(s1.toLowerCase())); // comparison is case-insensitive
        }
    }
    
}

public class Main {
	public static void main(String[] args) {
		String []products = {"mobile","mouse","moneypot","monitor","mousepad"};
		String searchWord = "mouse";
		System.out.println(new Solution().suggestedProducts(products, searchWord));
	}
}

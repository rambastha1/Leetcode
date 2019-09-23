#include <iostream>
#include <cstdlib>
#include <stack>
#include <climits>

using namespace std;

/* Method 1 using Inorder Traversal
 * 0(n) Time constant space
 */

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
}; 

class Solution {
public:
    TreeNode * root = nullptr;
    
    int kthSmallest(TreeNode* root, int k) {
       return kthSmallestUtil(root, k); 
    }
    
    // Go to Left most node - it is the smallest
    // If k == 0 return node
    // else decrement k if equal to 0 return root else check right of root
     
    int kthSmallestUtil(TreeNode* root, int& k) {
        if(root) {
            int x = kthSmallestUtil(root->left, k);
            return !k? x : !--k? root->val : kthSmallestUtil(root->right, k);
        }
    }    
    
    void inorder(TreeNode * node) {
        if(node == nullptr)
            return;
        inorder(node->left);
        cout << node->val << " ";
        inorder(node->right);
    }
    
    //Similar to solution
    void kthsmall(TreeNode* root, int k) {
        int c = 0;
        kthsmallUtil(root, k, c);
    }
    
    void kthsmallUtil(TreeNode* root, int k, int& c) {
        if(root && c <= k) {
            kthsmallUtil(root->left, k, c);
            c++;
            if(c == k) {
                cout << root->val << endl;
            }
            kthsmallUtil(root->right, k, c);
        }
    }
};

/*
 * Method 2 using left count.
 * if count of left child +1 == k -> root is the node
 * if k is less - >move root->left
 * else k = k- left count +1 gives nodes left to find in right child of root
 * then search root->right
 * 0(lgN) constant space but requires tree modification
 */

struct TreeNode2 {
    int val;
    int lcount;
    TreeNode2 *left;
    TreeNode2 *right;
    TreeNode2 (int x) : val(x), left(NULL), right(NULL), lcount(0) {}
};

class Solution2 {
public:
    TreeNode2 * root = nullptr;
    int kthSmallest(TreeNode2* root, int k) {
        int res = -1;
        if(root) {
            TreeNode2* temp = root;
            while(temp) {
                if(temp->lcount + 1 == k) {
                    res = temp->val;
                    break;
                } else if(k > temp->lcount) {
                    k -= temp->lcount + 1;
                    temp = temp->right;
                } else 
                    temp = temp->left;
            }
        }
        return res;
    }
    
    void inorder(TreeNode2 * node) {
        if(node == nullptr)
            return;
        inorder(node->left);
        cout << node->val << " ";
        inorder(node->right);
    }
    
    TreeNode2* insert(TreeNode2* root, int data) {
        TreeNode2* newnode = new TreeNode2(data);
        if(!root)
            return newnode;
        else if(data < root->val) {
            root->lcount++;
            root->left = insert(root->left, data);
        } else {
            root->right = insert(root->right, data);
        }
        return root;
    }
};

/*
 * Best Solution
 * Morris Traversal
 */

class Solution3 {
public:
    TreeNode* root = nullptr;
    int kth_small_morris(TreeNode* root, int k) {
        if(root == nullptr)
            return -1;
        
        int count = 0;
        int k_small = INT_MIN;
        TreeNode* curr = root;
        while(curr) {
            if(!curr->left) {
                count++;
                if(count == k)
                    k_small = curr->val;
                curr = curr->right;
            } else {
                TreeNode*  prev = curr->left;
                while(prev != nullptr && prev->right != nullptr && prev->right != curr) 
                    prev = prev->right;
                
                if(!prev->right) {
                    prev->right = curr;
                    curr = curr->left;
                } else {
                    prev->right = nullptr;
                    count++;
                    if(count == k)
                        k_small = curr->val;
                    curr = curr->right;
                }
            }
        }
        return k_small;
    }
    
    void inorder(TreeNode * node) {
        if(node == nullptr)
            return;
        inorder(node->left);
        cout << node->val << " ";
        inorder(node->right);
    }
};


int main(int argc, char** argv) {
    
    Solution3 s;
    s.root = new TreeNode(20);
    s.root->left = new TreeNode(8);
    s.root->left->left = new TreeNode(4);
    s.root->left->right = new TreeNode(12);
    s.root->left->right->left = new TreeNode(10);
    s.root->left->right->right = new TreeNode(14);
    s.root->right = new TreeNode(22);
    s.inorder(s.root);
    cout << endl;
    int k = 3;
    cout << s.kth_small_morris(s.root, k) << endl;
    
    /*Solution2 s;
    s.root = s.insert(s.root, 20);
    s.root = s.insert(s.root, 8);
    s.root = s.insert(s.root, 4);
    s.root = s.insert(s.root, 12);
    s.root = s.insert(s.root, 10);
    s.root = s.insert(s.root, 14);
    s.root = s.insert(s.root, 22);
    s.inorder(s.root);
    cout << endl;
    int k = 3;
    cout << s.kthSmallest(s.root, k) << endl;*/
    
    
    
    return 0;
}
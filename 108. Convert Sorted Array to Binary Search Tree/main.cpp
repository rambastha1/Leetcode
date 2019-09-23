#include <cstdlib>
#include <iostream>
#include <vector>

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Solution {
public:
    TreeNode* sortedArrayToBST(vector<int>& nums) {
        return sortedArrayToBSTUtil(nums, 0, nums.size()-1);
    }
    
    TreeNode* sortedArrayToBSTUtil(vector<int>& nums, int low, int high) {
        if(low > high)
            return nullptr;
        
        int mid = low + (high - low)/2;
        TreeNode*root = insert(nums[mid]);
        
        root->left = sortedArrayToBSTUtil(nums, low, mid-1);
        root->right = sortedArrayToBSTUtil(nums,mid+1,high);
        return root;
    }
    
    TreeNode* insert(int data) {
        TreeNode* newnode = (TreeNode*) malloc(sizeof(TreeNode));
        newnode->val = data;
        newnode->left = newnode->right = nullptr;
        return newnode;
    }
    
    void preorder(TreeNode* node) {
        if(node == nullptr)
            return;
        cout << node->val << " ";
        preorder(node->left);
        preorder(node->right);
    }
    
};

int main(int argc, char** argv) {
    
    return 0;
}
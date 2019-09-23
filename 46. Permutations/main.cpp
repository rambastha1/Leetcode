#include <iostream>
#include <cstdlib>
#include <vector>
#include <thread>

using namespace std;

class Solution {
public:
    vector<vector<int>> permute(vector<int>& nums) {
        vector<vector<int>> res;
        permuteUtil(nums, 0, res);
        return res;
    }
    
    void permuteUtil (vector<int>& nums, int begin, vector<vector<int>>& res) {
        if(begin >= nums.size()) {
            res.push_back(nums);
            return;
        } 
        
        for(int i = begin;i < nums.size();i++) {
            swap(nums[begin], nums[i]);
            permuteUtil(nums, begin+1, res);
            swap(nums[begin], nums[i]);
        }
    }
    
    /*vector<vector<int>> permute(vector<int>& nums) {
        vector<int> grandfather;
        vector<vector<int>> ans;
        put_down_an_element(nums,grandfather,ans);
        return ans;
    }
    
    void put_down_an_element(vector<int>& nums,vector<int>& parent_vec,vector<vector<int>>& ans){
        for(int i=0;i<nums.size();i++){//Go through all avaliable elements
            //1. Cp a parent vector and choose one element and push into this vec
            vector<int> parent_vec_cp=parent_vec;
            parent_vec_cp.push_back(nums[i]);
            
            //2. Cp a nums vector and remove the inserted element
            vector<int> nums_cp=nums;
            nums_cp.erase(nums_cp.begin()+i);
            
            //3. Recursively put down the rest elements
            put_down_an_element(nums_cp,parent_vec_cp,ans);
            
            //4. if nums_cp is empty, means all elements are placed into vector.
            //   Push this final vector into answer vector matrix and return
            if(nums_cp.size()==0){
                ans.push_back(parent_vec_cp);
                return;
            }
        }
    }*/
};

int main(int argc, char** argv) {
    vector<int> v = {1,2,3};
    vector<vector<int>> res = Solution().permute(v);
    for(auto v : res) {
        for(auto i : v) {
            cout << i << " ";
        }
        cout << endl;
    }
    return 0;
}


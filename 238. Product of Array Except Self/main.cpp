#include <iostream>
#include <cstdlib>
#include <vector>
#include <cstring>
#include <algorithm>

using namespace std;

class Solution {
    public:
        vector<int> productExceptSelf(vector<int>& nums) {
            int temp = 1;
            vector<int> res(nums.size());
            
            fill(res.begin(), res.end(), 1);           
            for(int i = 0;i < res.size();i++) {
                res[i] = temp;
                temp *= nums[i];
            }
            
            temp = 1;
            
            for(int i = res.size()-1;i >= 0;i--) {
                res[i] *= temp;
                temp *= nums[i];
            }
            return res;
        }
};

int main(int argc, char** argv) {
    vector<int> vec = {1,2,3,4};
    vec = Solution().productExceptSelf(vec);
    for(auto& i : vec)
        cout << i << " ";
    cout << endl;
    return 0;
}
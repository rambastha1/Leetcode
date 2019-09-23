#include <iostream>
#include <cstdlib>
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int rob(vector<int>& nums) {
        if(nums.size()==0)
            return 0;
        if(nums.size()==1)
            return nums[0];
        if(nums.size()==2)
            return max(nums[0],nums[1]);
        for(int i=2;i<nums.size();i++)
            nums[i]=nums[i]+ *max_element(nums.begin(),nums.begin()+i-1);
        return *max_element(nums.begin(),nums.end());
    }
};

int main(int argc, char** argv) {
    vector<int> vec = {1,2,3,1};
    cout << Solution().rob(vec) << endl;
    return 0;
}
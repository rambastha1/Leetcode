#include <iostream>
#include <cstdlib>
#include <vector>
#include <thread>
#include <algorithm>

using namespace std;

class Solution {
    public:
        vector<string> generateParenthesis(int n) {
        vector<string> res;
        generateParenthesisDFS(n, n, "", res);
        return res;
    }
    
    // left, right: remaining  #"(" or ")"
    void generateParenthesisDFS(int left, int right, string temp, vector<string>& res) {
        if (left > right) return ;
        if (left == 0 && right == 0) res.push_back(temp);
        else {
            if (left >0) generateParenthesisDFS(left-1, right, temp+"(", res);
            if (right>0) generateParenthesisDFS(left, right-1, temp+")", res);
        }
    }
};

int main(int argc, char** argv) {
    int n = 6;
    vector<string> res = Solution().generateParenthesis(n);
    for(auto& i : res) {
        cout << i << endl;
    }
    return 0;
}
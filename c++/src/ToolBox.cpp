#include "ToolBox.h"
#include <bits/stdc++.h>


 vector<string> split(string str, string delim) {
    vector<string> res;
    auto start = 0u;
    auto end = str.find(delim);
    while (end != string::npos) {
        res.emplace_back(str.substr(start, end - start));
        start = end + delim.length();
        end = str.find(delim, start);
    }
    res.emplace_back(str.substr(start));

    return res;
}

 vector<int> input(string str) {
     vector<int> res;
     for (auto& i : split(str.substr(1, str.size() - 1), ",")) {
         res.emplace_back(stoi(i));
     }
     return res;
 }

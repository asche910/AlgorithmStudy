
#ifndef ToolBox_H
#define ToolBox_H

#include<bits/stdc++.h>
using namespace std;

struct ListNode {
	int val;
	ListNode* next;
	ListNode(int x) : val(x), next(nullptr) {}
};

vector<string> split(string, string);

#endif

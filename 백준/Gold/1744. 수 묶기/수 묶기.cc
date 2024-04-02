#include<iostream>
#include<queue>

using namespace std;

struct compare{
    bool  operator()(int a,int b)
    {
      return a>b;
    }
};

priority_queue<int,vector<int>,compare> npq; // 음수를 저장하는 우선순위큐
priority_queue<int> ppq;  //양수를 저장하는 우선순위큐

int main(void)
{


  int T;
  cin>>T;
  while(T--)
  {
    int tmp;
    cin>>tmp;
    if(tmp>0)
    {
      ppq.push(tmp);
    }
    else if(tmp<=0)
    {
      npq.push(tmp);
    }
  }

   int ans=0;

  while(!ppq.empty())
  {
    int temp_num;
    temp_num=ppq.top();
    ppq.pop();
    if(!ppq.empty())
    {
      if(ppq.top()!=1)
      temp_num*=ppq.top();
      else
      temp_num+=ppq.top();  // 1+1>1*1
      ppq.pop();
    }
    ans+=temp_num;
  }

  while(!npq.empty())
  {
    int temp_num;
    temp_num=npq.top();
    npq.pop();
    if(!npq.empty())
    {
      temp_num*=npq.top();
      npq.pop();
    }
    ans+=temp_num;
  }

  cout<<ans;



  return 0;


}


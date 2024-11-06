string m = "m_str";

int func_scope(int a, string s)
{
    int m = 200;
    int b = m + 1;
    return a + b;
}

string mm = m + "append_str";

int n = func_scope(100, "param_str");
Console.WriteLine(n);
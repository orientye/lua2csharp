(int, string) FuncReturn(string s, int a)
{
    int m = 200;
    int b = m + 1;
    return (a + b, s);
}

int n = FuncReturn("param_str", 100).Item1;
(int n1, string s1) = FuncReturn("param_str1", n);
Console.WriteLine(n1);
Console.WriteLine(s1);
int n2 = FuncReturn("param_str2", 200).Item1;
(int n3, string s3) = FuncReturn("param_str3", n2);
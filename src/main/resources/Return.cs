(int, string) FuncReturn(string s, int a)
{
    int m = 200;
    int b = m + 1;
    return (a + b, s);
}

var (n, _) = FuncReturn("param_str", 100);
var (n1, s1) = FuncReturn("param_str2", n);

Console.WriteLine(n);
Console.WriteLine(n1);
Console.WriteLine(s1);
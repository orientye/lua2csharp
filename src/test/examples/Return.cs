(int, string) func_return(string s, int a)
{
    int m = 200;
    int b = m + 1;
    return (a + b, s);
}

(int n, _) = func_return("param_str", 100);
(int n1, string s1) = func_return("param_str1", n);
Console.WriteLine(n1);
Console.WriteLine(s1);
(int n2, _) = func_return("param_str2", 200);
(int n3, string s3) = func_return("param_str3", n2);
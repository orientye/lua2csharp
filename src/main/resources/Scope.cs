using System;

class Program
{
    static string m = "m_str";

    static int FuncScope(int a)
    {
        int m = 200;
        int b = m + a;
        return b;
    }

    static void Main()
    {
        int n = FuncScope(100);
        Console.WriteLine(n);
    }
}

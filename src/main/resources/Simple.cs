using System;

class Program
{
    static void Main(string[] args)
    {
        // first comment
        int i = 10;
        // second comment
        Console.WriteLine(i);

        string s = "hello";
        Console.WriteLine(s);

        int? n = null;
        bool bt = true;
        bool bf = false;

        n = FuncSum(1, 2);
        Console.WriteLine(n);
    }

    static int FuncSum(int a, int b)
    {
        return a + b;
    }
}

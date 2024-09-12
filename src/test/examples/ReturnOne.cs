int func_return(int a)
{
    int m = 200;
    int b = m + 1;
    return a + b;
}

int n = func_return(100);
Console.WriteLine(n);
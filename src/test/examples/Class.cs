T t = new T { name = "Bob" };

public static void test()
{
    Console.WriteLine("This is a test");
}

test();

class T
{
    public string name { get; set; }

    public void sayHello()
    {
        Console.WriteLine($"Hi {name}");
        Console.WriteLine($"Hello {name}");
    }
}

t.sayHello();
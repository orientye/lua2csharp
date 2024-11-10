T t = new T { name = "Bob" };

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
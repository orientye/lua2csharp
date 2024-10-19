T t = new T { Name = "Bob" };

class T
{
    public string Name { get; set; }

    public void SayHello()
    {
        Console.WriteLine($"Hi {Name}");
        Console.WriteLine($"Hello {Name}");
    }
}

t.SayHello();
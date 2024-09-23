var t = new T { Name = "Bob" };

class T
{
    public string Name { get; set; }

    public void SayHello()
    {
        Console.WriteLine($"Hello {Name}");
        Console.WriteLine($"Second {Name}");
    }
}

t.SayHello();
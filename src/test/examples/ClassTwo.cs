T t = new T { Name = "Bob" };
Other s = new Other { Id = 100 };

class T
{
    public string Name { get; set; }

    public void SayHello()
    {
        Console.WriteLine($"Hi {Name}");
        Console.WriteLine($"Hello {Name}");
    }

    public void SayWorld(Other other)
    {
        Console.WriteLine("World " + Name);
        Console.WriteLine("Hello " + Name);
        Console.WriteLine("Id " + other.Id);
    }
}

class Other
{
    public int Id { get; set; }
}

t.SayHello();
t.SayWorld(s);
t = { name = "Bob" }

function test()
    print("this is a test");
end

test();

function t:sayHello()
    print("Hi " .. self.name)
    print("Hello " .. t.name)
end

t:sayHello()
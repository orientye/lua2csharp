t = { name = "Bob" }

function t:sayHello()
    print("Hello" .. self.name)
    print("Second" .. t.name)
end

t:sayHello()
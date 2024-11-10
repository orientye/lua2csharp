t = { name = "Bob" }

function t:sayHello()
    print("Hi " .. self.name)
    print("Hello " .. t.name)
end

t:sayHello()
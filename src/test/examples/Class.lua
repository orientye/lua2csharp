t = { name = "Bob" }

function t:sayHello()
    print("Hello" .. self.name)
end

t:sayHello()
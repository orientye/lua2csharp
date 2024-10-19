t = { name = "Bob" }

function t:sayHello()
    print("Hello " .. self.name)
    print("Hello " .. t.name)
end

s = { id = 100 }
function t.sayWorld(self, other)
    print("World " .. self.name)
    print("World " .. t.name)
    print("Id " .. other.id)
end

t:sayHello()
t.sayWorld(t, s)
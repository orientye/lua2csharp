t = { name = "Bob" }

function t:sayHello()
    print("Hello" .. self.name)
    print("Second" .. t.name)
end

t2 = { name = "Mike" }
function t.sayWorld(self, atable)
    print("Hello" .. self.name)
    print("Second" .. t.name)
    print("Third" .. atable.name)
end

t:sayHello()
t.sayWorld(t, t2)
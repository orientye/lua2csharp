local CustomClass = class("CustomClass")

function CustomClass:ctor()
    self.id_ = 0
    self.name_ = "lua2csharp"
end

function CustomClass:getId()
    return self.id_;
end

return CustomClass
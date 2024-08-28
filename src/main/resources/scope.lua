
local m = "m_str";

function func_scope(a, s)
    local m = 200;
    local b = m + 1;
    return b;
end

local mstr = m + "_str"

n = func_scope(100, "param_str");
print(n);
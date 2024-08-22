
local m = "m_str";

function func_scope(a)
    local m = 200;
    local b = m + a;
    return b;
end

n = func_scope(100);
print(n);
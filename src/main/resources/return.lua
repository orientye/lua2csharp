function func_return(s, a)
    local m = 200;
    local b = m + 1;
    return a + b, s;
end

n1, s1 = func_return("param_str2", 1);
print(n1);
print(s1);
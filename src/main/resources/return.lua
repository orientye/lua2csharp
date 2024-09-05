function func_return(s, a)
    local m = 200;
    local b = m + 1;
    return a + b, s;
end

n = func_return("param_str", 100);
n1, s1 = func_return("param_str2", n);
print(n);
print(n1);
print(s1);
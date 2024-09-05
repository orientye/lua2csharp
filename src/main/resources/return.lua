function func_return(a, s)
    local m = 200;
    local b = m + 1;
    return a + b, s;
end

n = func_return(100, "param_str");
n1, s1 = func_return(n, "param_str2");
print(n);
print(n1);
print(s1);
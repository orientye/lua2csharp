function func_return(s, a)
    local m = 200;
    local b = m + 1;
    return a + b, s;
end

n = func_return("param_str", 100);
n1, s1 = func_return("param_str1", n);
print(n1);
print(s1);
local n2 = func_return("param_str2", 200);
local n3, s3 = func_return("param_str3", n2);
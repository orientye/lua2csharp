function func_void()
    print("I am a void function");
end
func_void();

local function func_local_void()
    print("I am a void local function");
end
func_local_void();

function func_void_has_param(a)
    print(a);
end
func_void_has_param(1);

local function func_local_void_has_param(b)
    print(b);
end
func_local_void_has_param(2);
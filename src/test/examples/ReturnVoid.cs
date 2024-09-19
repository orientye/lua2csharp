void func_void()
{
    Console.WriteLine("I am a void function");
}
func_void();

void func_local_void()
{
    Console.WriteLine("I am a void local function");
}
func_local_void();

void func_void_has_param(int a)
{
    Console.WriteLine(a);
}
func_void_has_param(1);

void func_local_void_has_param(int b)
{
    Console.WriteLine(b);
}
func_local_void_has_param(2);
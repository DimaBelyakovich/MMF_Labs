a = 1;
b = 2;
n = 50;
h = (b - a) / n;
xExact = 1:1 / n:2;
yExact = 1/2 * (xExact - xExact.^(-1));
F = @(x,y) ((1 + x.*y) / (x^2));
x(1) = a;
y(1) = 0;
for i = 1:n-1
    x(i+1) = a + i*h;
    y(i+1) = y(i) + h*F(x(i),y(i));
end
plot(x,y,'r',xExact,yExact,'.b'); grid on;
x = [0.5:0.1:3.5];
eps = 1e-11; 
f=@(x) 2 * (cos(x)).^2 - 2 + x.^3;
diff_f =@(x) diff(f(x));
tau = (-2/max(diff_f(x)));
fi =@(x) x + tau*f(x);

figure();
plot(X, X);
hold on;
plot(X,fi(X));

x0 = 0.5; 
N = 1000; 
x1 = fi(x0);

iter = 1;
error = 1;

for i = 1 : N
    if abs(x1 - x0) <= eps
        break;
    endif
    x0 = x1;
    x1 = fi(x0);
    iter=iter+1;
    error(iter)=abs(x1 - x0);
endfor

figure();
loglog(error)
disp(x1);
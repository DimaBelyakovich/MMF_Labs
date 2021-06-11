clear;
clc;

function y = p(x)
  y = - ( 6 * x) / (3 * x.^2 - 1/2);
endfunction
function y = q(x)
  y = 1 / x;
endfunction
function y = f(x)
  y = 1 / 2 - x.^2;
endfunction
function y = f_0(x)
  y = 0;
endfunction

alpha0 = 0; betta0 = 1; gamma0 = 1/4;
alpha1 = 2; betta1 = 1; gamma1 = 3.5;

a = 0.5; b = 1;

tau = 0.01;
n = (b-a)/tau + 1;
X = a:tau:b;

u = @(x) x * (x.^2 - 1/2);

function [Z1, Z2] = solution(p, q, f, a, b, tau, init_val, n)
  x = a:tau:b;
  Z1(1) = init_val(1);
  Z2(1) = init_val(2);
  for k = 1:n - 1
    Z1(k + 1) = Z1(k) + tau * (-Z1(k) * Z1(k) -p(x(k)) * Z1(k) + q(x(k)))
    Z2(k + 1) = Z2(k) + tau * (-Z2(k) * (Z1(k) + p(x(k))) + f(x(k)))
  endfor
endfunction


init_vals = [-alpha0 / betta0 gamma0 / betta0];

[Z1, Z2] = solution(@p, @q, @f, a, b, tau, init_vals, n);

y = zeros(n, 1);

y(n) = (gamma1 - betta1 * Z2(end)) / (alpha1 + betta1 * Z1(end));
for k = 1:n - 1
  y(n - k) = y(n - k + 1) - tau * (Z1(n - k + 1) * y(n - k + 1) + Z2(n - k + 1));
endfor

fplot(u, [min(X), max(X)]);
hold on;
scatter(X, y, 10, "filled");
legend("Exact solution", "run-through method");
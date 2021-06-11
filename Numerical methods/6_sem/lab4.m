clear; clc;

lambda = -2;
right_side = @(t, u) lambda*u;

u0 = 1;
t0 = 0;
tk = 10;
tau = 0.01;

t_ab = t0:tau:tk;
u_ab = zeros(size(t_ab));
n = length(t_ab);
u_ab(1) = u0;

f = zeros(size(t_ab));
f(1) = right_side(t_ab(1), u0);
b = [55/24, -59/24, 37/24, -9/24];

for k = 1:3
  k1 = tau * right_side(t_ab(k), u_ab(k));
  k2 = tau * right_side(t_ab(k) + tau/2, u_ab(k) + k1/2);
  k3 = tau * right_side(t_ab(k) + tau/2, u_ab(k) + k2/2);
  k4 = tau * right_side(t_ab(k) + tau, u_ab(k) + k3);
  u_ab(k+1) = u_ab(k) + (k1 + 2*k2 + 2*k3 + k4)/6;
  f(k+1) = right_side(t_ab(k+1), u_ab(k+1));
end

for k = 4:n-1
  tmp = 0;
  for i = 1:4
    tmp += b(i) * f(k+1-i);
  end
  u_ab(k+1) = u_ab(k) + tau*tmp;
  f(k+1) = right_side(t_ab(k+1), u_ab(k+1));
end

[t_rk, u_rk] = ode45(right_side, [t0, tk], u0);
t_exact = t0:tau:tk;
u_exact = exp(lambda*t_exact);
figure
plot(t_ab, u_ab, 'b', t_rk, u_rk, 'r*', t_exact, u_exact, 'g-.');
legend('AB-4', 'RK-4', 'Exact');
err = abs(u_exact-u_ab);
figure
semilogy(err);
legend('AB-4 error');
err = zeros(1, length(t_rk));

for k = 1:length(t_rk)
  err(k) = abs(exp(lambda*t_rk(k)) - u_rk(k));
end  

figure
semilogy(err);
legend('RK-4 error');
tau = 1/abs(lambda) + 0.1;
t_ab = t0:tau:tk;
u_ab = zeros(size(t_ab));
n = length(t_ab);
u_ab(1) = u0;
f = zeros(size(t_ab));
f(1) = right_side(t_ab(1), u0);

for k = 1:3
  k1 = tau * right_side(t_ab(k), u_ab(k));
  k2 = tau * right_side(t_ab(k) + tau/2, u_ab(k) + k1/2);
  k3 = tau * right_side(t_ab(k) + tau/2, u_ab(k) + k2/2);
  k4 = tau * right_side(t_ab(k) + tau, u_ab(k) + k3);
  u_ab(k+1) = u_ab(k) + (k1 + 2*k2 + 2*k3 + k4)/6;
  f(k+1) = right_side(t_ab(k+1), u_ab(k+1));
end

for k = 4:n-1
  tmp = 0;
  for i = 1:4
    tmp += b(i) * f(k+1-i);
  end
  u_ab(k+1) = u_ab(k) + tau*tmp;
  f(k+1) = right_side(t_ab(k+1), u_ab(k+1));
end

t_exact = t0:tau:tk;
u_exact = exp(lambda*t_exact);
figure
plot(t_ab, u_ab, 'b', t_exact, u_exact, 'g-.');
legend('AB-4', 'Exact');
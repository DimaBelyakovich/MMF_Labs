clear; clc;

function [f] = right_side(t, u)
    f = (t - u) / (t - 2 * u);
end

function [t, u] = ode_rk4(right_side, u0, t0, tk, tau)
    t = t0:tau:tk;
    u = zeros(size(t));
    n = length(t);
    u(1) = u0;

    for k = 1:n-1
        k1 = tau * right_side(t(k), u(k));
        k2 = tau * right_side(t(k)+tau/2, u(k)+k1/2);
        k3 = tau * right_side(t(k)+tau/2, u(k)+k2/2);
        k4 = tau * right_side(t(k)+tau, u(k)+k3);

        u(k+1) = u(k) + 1/6 * (k1 + 2*k2 + 2*k3 + k4);
    end
end

f = @right_side;
u0 = 1;
t0 = 0;
tk = 0.3;
tau = 0.03;

[t,u] = ode_rk4(f, u0, t0, tk, tau);
[t1, sol] = ode45(f, [t0, tk], u0);

plot(t, u, 'r.-', t1, sol, 'bo');
legend('R-K','Exact solution');

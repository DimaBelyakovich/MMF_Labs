clear; clc;

global p;
p = @(t) -( 6 * t) / (3 * t.^2 - 1/2);
global q;
q = @(t) 1 / t;
global func;
func = @(t) 1 / 2 - t.^2;
u_exact = @(x) x .* (x.^2 - 1/2);

a0 = 0;
a1 = 2;
b0 = 1;
b1 = 1;
g0 = 0.25;
g1 = 3.5;

t_segment = 0.5:0.01:1;
N = length(t_segment);

function [f] = right_side_with_f(t, u)
    f = zeros(2, 1);
    global p;
    global q;
    global func;
    f(1) = u(2); %T'
    f(2) = -p(t)*u(2) + q(t)*u(1) + func(t); %S'
end

exact_solution = zeros(1, N);
for i = 1:N
    exact_solution(i) = u_exact(t_segment(i));
end

t_0 = rand * 131071;
t_1 = rand * 8191;

if b0 ~= 0
    u0_Z1 = [t_0, (g0 - a0*t_0) / b0];
    u0_Z2 = [t_1, (g0 - a0*t_1) / b0];
else
    u0_Z1 = [g0 / a0, t_0];
    u0_Z2 = [g0 / a0, t_1];
end

[t_segment_Z1, solution_Z1] = ode45(@right_side_with_f, t_segment, u0_Z1);
[t_segment_Z2, solution_Z2] = ode45(@right_side_with_f, t_segment, u0_Z2);

Z1 = solution_Z1(:,1);
derivative_Z1 = solution_Z1(:,2);
Z2 = solution_Z2(:,1);
derivative_Z2 = solution_Z2(:,2);

numerator = g1 - a1 * Z1(end) - b1 * derivative_Z1(end);
denominator = a1 * (Z2(end) - Z1(end)) + b1 * (derivative_Z2(end) - derivative_Z1(end));
C = numerator / denominator;

U = (1 - C) * Z1 + C * Z2;

hold on;
plot(t_segment, exact_solution);
plot(t_segment, U, 'ro');
legend('exact solution', 'pristrelka');
title('pristrelka');
xlabel('x');
ylabel('u');


n = 7;
N = n.^2;
eps = 1.e-5;
A = gallery('poisson', n);
f = rand(N, 1);
numb = 1000;

function [Error] = iteration_method(B, A, f)
  k_numb = 1000;
  D = inv(B) * A;
  g = inv(B) * f;
  x = zeros(length(D), 1);
  r = D * x - g;
  err = norm(r) / norm(g);
  k = 0;
  Error = zeros(k_numb, 1);
  
  while err > eps && k < k_numb
    tau = (r' * r) / ((A * r)' * r);
    x = x - tau * r;
    r = D * x - g;
    err = norm(r) / norm(g);
    k = k + 1;
    Error(k) = err;
  endwhile
  
endfunction
%-----------------------------------
B = diag(diag(A));
jakobi = iteration_method(B, A, f);
%-----------------------------------
B1 = triu(A);
zeidel = iteration_method(B1, A, f);
%-----------------------------------
omega = 0.5;
E = eye(size(A));
R1 = zeros(size(A));
R2 = zeros(size(A));
for k = 1: length(A)
  for m = 1: length(A)
    if k > m
      R1(k,m) = A(k,m);
      R2(k,m) = 0;
    elseif k < m
      R1(k,m) = 0;
      R2(k,m) = A(k,m);
    else
      R1(k,m) = A(k,m)/2;
      R2(k,m) = A(k,m)/2;
    endif
  endfor
endfor
B2 = (E + omega*R1)*(E+ omega*R2);
triangle = iteration_method(B2,A,f);

x = zeros(numb,1);
for i = 1:numb
  x(i) = i;
endfor

plot(x, jakobi, x, zeidel, x, triangle)
legend("Jakobi", "Zeidel", "Triangle")


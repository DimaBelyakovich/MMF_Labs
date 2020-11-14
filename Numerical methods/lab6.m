n = 7;
v = 2;
eps = (1/2) * 10**(-3);

z = zeros(n, 1);
y = zeros(n, 1);
A = zeros(n);
for i = 1:n
  A(i, i) = v + 10 / (10 + i);
  
  if i <= n - 1
    A(i, i + 1) = v + 10 / i;
    A(i + 1, i) = v + 10 / i;
  endif
  
  if i <= n - 2
    A(i, i + 2) = v + (1 + i) / 10;
    A(i + 2, i) = v + (1 + i) / 10;
  endif
  z(i) = rand();
  y(i) = rand();
endfor

max_lambda = 1;
min_lambda = 0;
while abs(max_lambda - min_lambda) >= eps  
  z = y / norm(y);
  y = A * z;
  lambda = y ./ z;
  max_lambda = max(lambda);
  min_lambda = min(lambda);
endwhile

lambda_n = dot(y, z) / dot(z, z);
lambda_s = 0;
while abs(lambda_n - lambda_s) >= eps  
  z = y / norm(y);
  y = A * z;
  lambda_s = lambda_n;
  lambda_n = dot(y, z) / dot(z, z);
endwhile

max(eigs(A, n)) 

display(max_lambda);
display(lambda_n);
display(z);


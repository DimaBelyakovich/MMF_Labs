v = 1;
a = -5;
b = 4;

n = 10;

e = 10.^-3;

A = zeros(n);
f = zeros(n,1);

for i=1:n
  f(i) = (b-a)*rand;
  for j=1:n
    if i == j
      A(i,j) = 100 + v;
    else
      A(i,j) = 1/(i+j+v);
    endif
  endfor
endfor

param_t = [1/(2*norm(A)), 1/(4*norm(A)), 1/(8*norm(A))];
pogresh = zeros(length(param_t));

for i = 1:length(param_t)
  H = eye(n) - param_t(i)*A;
  phi = param_t(i)*f(i);
  q = norm(H);
  xs = phi;
  xn = H*xs + phi;
  k = 0;
  while (q/(1-q))*norm(xn-xs) > e
    xs = xn;
    xn = H*xs + phi;
    k = k + 1;
    pogresh(i,k) = (q/(1-q))*norm(xn-xs);
  endwhile
endfor

x = zeros(length(pogresh),1);
for i = 1:length(pogresh)
    x(i) = i;
endfor

plot(x, pogresh(1, :), x, pogresh(2, :), x, pogresh(3, :));
legend ("t=1/(2|A|)", "t=1/(4|A|)", "t=1/(8|A|)");
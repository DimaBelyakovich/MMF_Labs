%Belyakovich Dmitry, 1st variant
a = 7;
b = 8;
v = 1;

function [x] = SquareRoot(A,f)
  S = zeros(size(A));
  D = zeros(size(A));
  n = size(A)(1);
  
  D(1,1) = sign(A(1,1));
  S(1,1) = sqrt(A(1,1));
  
  for i = 2:n
    DSum = 0
    for k = 1:i-1
      DSum = DSum+(abs(S(k,i)).^2)*D(k,k);
    end
    D(i,i) = sign(A(i,i)-DSum);
    
    SSum = 0
    for k = 1:i-1
      SSum = SSum+(abs(S(k,i)).^2)*D(k,k);
    end
    S(i,i) = sqrt(A(i,i)-SSum);
    
    for j = i + 1:n
      tsum = 0;
      for k = 1:i-1
        tsum = tsum + S(k,i)*D(k,k)*S(k,j);
      endfor
      S(i,j) = ( A(i,j) - tsum )/(S(i,i)*D(i,i));
    endfor
  endfor
  %%%%%%%%%%%%%%%%%%
  y = find_y(f,S,D,n);
  %%%%%%%%%%%%%%%%%%
  x = find_x(y, S,n);
endfunction

function y = find_y(f, S, D, n)
  Y = zeros(n,1);
  Y(1) = f(1) / (S(1,1)*D(1,1)); 
  for i=2:n
    tsum = 0;
    for j=1:i-1
      tsum = tsum + S(j,i)*Y(j)*D(j,j);
    endfor
    Y(i) = (f(i)-tsum)/(S(i,i)*D(i,i));
  endfor
  y=Y;
endfunction

function x = find_x(y,S,n)
  X = zeros(n,1);
  X(n) = y(n)/S(n,n);
  for i = n-1:-1:1
    tsum = 0;
    for j = i+1:n
      tsum = tsum + S(i,j)*X(j);
    endfor
    X(i) = (y(i) - tsum)/S(i,i);
  endfor
  x = X;
endfunction

n_list = [4,6,8,10,12];
nev = zeros(size(n_list)(2));

for z = 1:size(n_list)(2)
    n = n_list(z);
    A = zeros(n);
    f = zeros(n, 1);
    for i = 1:n
        f(i) = a + (b - a) * rand;
        for j = 1:n
            if (i == j)
                A(i, j) = 1 / (i + j + v);
            endif
        endfor
    endfor
    
    x = SquareRoot(A,f)
    
    nev(z) = norm (f - A * x) / norm(f);
    
endfor

plot(n_list, nev, '-');
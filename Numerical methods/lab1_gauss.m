%Белякович Дмитрий, 3ий курс, 9ая группа, 1ый вариант
n1 = 5;
A = zeros(n1,n1);
b = zeros(n1,1);
E = eye(n1,n1);

for i=1:n1
  for k=1:n1
    if i==k 
      A(i,k) = 1 + 1/k;
    else
      A(i,k) = (n1-k)^2;
    end
  end
  b(i) = 1/i;
end

function z=Simplex(A,b)
  [m,n]=size(A);
  C=cat(2,A,b);
  for j=1:m-1
      for z=2:m
          if C(j,j)==0
              t=C(j,:);C(j,:)=C(z,:);
              C(z,:)=t;
          end
      end
      for i=j+1:m
          C(i,:)=C(i,:)-C(j,:)*(C(i,j)/C(j,j));
      end
  end
  z=C;
endfunction

function z=Determ(C)
  n = size(C,1);
  d = 1;
  for i=1:n
    for k=1:n
      if i==k 
        d = d * C(i,k);
      end
    end
  end
  z=d;
endfunction

function z=Gauss(A,b)
  C=Simplex(A,b);
  N=size(A,1);
  v(N)=C(N,N+1);
  for j=1:N-1
    s=0;
    for k=0:j-1
      s=s+C(N-j,N-k)*v(N-k);
    end;
    v(N-j)=(C(N-j,N+1)-s)/C(N-j,N-j);
  end;
  z=v';
endfunction

function z=InvMatrix(C)
  n = size(C,1);
  E = eye(n,n);
  X = zeros(n);
  for i=1:n
    x = Gauss(C,E(:,i));
    X(:,i) = x;
  endfor
  z=X;
endfunction


disp('Simpl');
S = Simplex(A,b)
disp('Det');
Determ(S)
det(A)
disp('Invert');
InvMatrix(A)
inv(A)





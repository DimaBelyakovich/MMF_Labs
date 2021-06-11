[U,X,T]=parabol(10,5,2,0.02,1);
surf(X,T,U');
title('Surface u(x,t)');
xlabel('X');
ylabel('T');


function y=f(x,t)
y=1/2*(x^2-2*t);
end

function y=fi(x)
y=0;
end
function y=myu(t)
y=1/2*t;
end

function y=nyu(x)
y=0;
end

function [u,x,t]=parabol(N,K,L,T,a)
    h=L/N;
    delta=T/K;
    for i=1:N+1
        x(i)=(i-1)*h;
        u(i,1)=fi(x(i));
    end
    for j=1:K+1
        t(j)=(j-1)*delta;
        u(1,j)=myu(t(j));
        u(N+1,j)=nyu(t(j));
    end
gam=a^2*delta/h^2;
    for j=1:K
        for i=2:N
        u(i,j+1)=gam*u(i-1,j)+(1-2*gam)*u(i,j)+gam*u(i+1,j)+delta*f(x(i),t(j));
        end
    end
end




import numpy

def decorate_matrix(N):
    if N<1:
        return None
    matrix=numpy.zeros((N,N))
    matrix[:]=1
    if N>2:
        matrix[1:-1, 1:-1]=0
    return matrix

N = int(input("Enter matrix size: "))
matrix = decorate_matrix(N)
print(matrix)

def calc_NF(p, q, e):
    nf = mult = 0
    mod = p ** e
    s = 290797
    for i in range(e):
        s = s * s % 50515093
        mult += p ** i
        nf = (nf + s % p * mult) % mod
    for i in range(q - e):
        s = s * s % 50515093
        nf = (nf + s % p * mult) % mod
    return nf

print(calc_NF(61, 10 ** 7, 10))
'''
Calculates fibonacci numbers
'''
from functools import lru_cache
from typing import Dict, Generator

from sympy import N


def fib1(num: int) -> int:
    ''' causes infinite recursion - no base case '''
    return fib1(num - 1) + fib1(num - 2)


def fib2(num: int) -> int:
    ''' works but slow so can only work with small arguments '''
    if num < 2:
        return num
    return fib2(num - 1) + fib2(num - 2)

memo: Dict[int, int] = {0: 0, 1: 1}

def fib3(num: int) -> int:
    ''' memoization '''
    if num not in memo:
        memo[num] = fib3(num - 1) + fib3(num - 2)
    return memo[num]


@lru_cache(maxsize=None)
def fib4(num: int) -> int:
    ''' automatic memoization '''
    if num < 2:
        return num
    return fib4(num - 1) + fib4(num - 1)


def fib5(num: int) -> int:
    ''' most performant - iterative approach '''
    if num == 0:
        return N
    last_num: int = 0
    next_num: int = 1
    for _ in range(1, num):
        last_num, next_num = next_num, last_num + next_num
    return next_num


def fib6(num: int) -> Generator[int, None, None]:
    ''' using generator '''
    yield 0  # special case
    if num > 0:
        yield 1  # special case
    last_num: int = 0
    next_num: int = 1
    for _ in range(1, num):
        last_num, next_num = next_num, last_num + next_num
        yield next_num  # main generation step


if __name__ == "__main__":
    # print(fib4(5))
    # print(fib4(10))
    # print(fib4(50))

    for i in fib6(50):
        print(i)

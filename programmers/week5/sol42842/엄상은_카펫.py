import math

def solution(brown, yellow):    
    # 2 * w + 2 * h - 4 = brown
    # w * h = brown + yellow
    w = 1 + brown / 4 + 1 / 2 * math.sqrt((2 + brown / 2) * (2 + brown / 2) - 4 * (brown + yellow))
    w = int(w)
    h = (brown + yellow) / w
    h = int(h)
    answer = [w, h]
    return answer
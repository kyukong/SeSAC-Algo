import itertools

def solution(k, dungeons):
    answer = -1
    # 계산 수 8!
    for x in itertools.permutations(dungeons, len(dungeons)):
        k_now = k
        count = 0
        d_order = list(x)
        for d in d_order:
            if (k_now >= d[0]):
                count += 1
                k_now -= d[1]
            else:
                break
        answer = max(answer, count)
    return answer
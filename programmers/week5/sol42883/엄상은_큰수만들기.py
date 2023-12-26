def solution(number, k):
    answer = ''
    i = 0
    while (i < len(number)): # i번째 문자열
        # i부터 i+k 문자열까지 확인해보기
        max_value = -1
        for j in range(i, i+k+1):
            if (max_value < int(number[j])):
                max_value = int(number[j])
                max_index = j
                if (int(number[j]) == 9):
                    break
        k -= (max_index - i)
        answer += str(max_value)
        i = max_index + 1
        if (k == 0):
            answer += number[i:]
            break
        if (k == len(number) - i):
            break
    return answer
def solution(numbers, target):            
    answer = 0
    r = [-1 * (target)]
    for i in range(len(numbers)):
        new_r = []
        # i번째 원소로 계산 numbers[i]
        # result를 이용해 2^(i+1)번 계산 필요
        for j in r:
            left = j + numbers[i]
            right = j - numbers[i]
            if (i == len(numbers) - 1):
                if (left == 0):
                    answer += 1
                if (right == 0):
                    answer += 1
            else:
                new_r.append(left)
                new_r.append(right)
        r = new_r
    return answer
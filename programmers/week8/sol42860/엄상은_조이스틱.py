def solution(name):
    answer = 0
    # 문자 c에서의 커서 횟수
    for c in name:
        for i in range(14):
            if chr(ord('A')+i) == c or chr(ord('Z')-i+1) == c:
                answer += i
                break
    lr_count = len(name)-1
    
    # 연속 a의 start, end, 연속 개수 저장하기
    a_list = []
    is_a = False
    len_a = 0
    for i in range(len(name)):
        if name[i] == 'A':
            len_a += 1
            is_a = True
            if i == len(name)-1:
                a_list.append((i-len_a+1, i, len_a))
        else:
            if is_a:
                a_list.append((i-len_a, i-1, len_a))
                len_a = 0
                is_a = False
    
    for a_l in a_list:
        s, e, c = a_l
        # a 기준 왼쪽을 먼저 다 훑고 오른쪽을 다 훑기
        # a 기준 오른쪽을 먼저 다 훑고 왼쪽을 다 훑기
        if s == 0 and e == len(name)-1:
            lr_count = min(lr_count, 0)
        elif s == 0:
            lr_count = min(lr_count, 1 + (len(name) - 2 - e))
        elif e == len(name)-1:
            lr_count = min(lr_count, s-1)
        else: 
            lr_count = min(lr_count, 2 * (s-1) + 1 + (len(name) - 2 - e), 2 + 2 * (len(name) - 2 - e) + s-1)
    answer += lr_count 
    return answer
from itertools import product

def solution(users, emoticons):
    # users: (일정비율) (일정가격)
    # emoticons: (가격)
    
    sale_percent = [10, 20, 30, 40]
    
    max_join = 0
    max_price = 0
    
    for order in product(sale_percent, repeat=len(emoticons)):
        join_count = 0
        total_price = 0            
        for u in users:
            price = 0
            for i in range(len(emoticons)):
                if u[0] <= order[i]: # order[i]는 i번째 이모티콘의 할인율
                    price += emoticons[i] * (1 - order[i] / 100) # 이모티콘 구매
            if price >= u[1]: # 일정 가격 이상 구매
                join_count += 1
            else:
                total_price += price
        
        if max_join < join_count:
            max_join = join_count
            max_price = total_price
        elif max_join == join_count:
            max_price = max(max_price, total_price)

    answer = [max_join, max_price]
    return answer
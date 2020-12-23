from collections import Counter

with open("day6/answers.txt", "r") as file:
    answers = file.read().split("\n\n")

answers = [s.split("\n") for s in answers]



#question 1
count = 0

for ls in answers:
    ansString = ''.join(ls)
    count += len(set(ansString))

print(count)

#question 2
count2 = 0

for ls in answers:
    ansString = ''.join(ls)
    countedCharacters = Counter(ansString)
    ansStringSet = set(ansString)
    for c in ansStringSet:
        if(countedCharacters[c] == len(ls)): count2 += 1

print(count2)


import random
a=random.randint(1,50)
print(a)
correctEntry = False
predictionCheck = False

while predictionCheck==False:
    
    while correctEntry == False:
        
        c=input('\ncheck divisibility[d], range[r], guess number[n]')
        
        if c =='d' or c =='r' or c =='n':
            if c=='d':
                divCheck = int(input('\nenter number for divisibility check'))
                if a%divCheck==0:
                    print('number is divisible by ',divCheck)
                else:
                    print('number is not divisible by ',divCheck)

            if c=='r':
                rangeCheck1=int(input('\nenter smaller range limit'))
                rangeCheck2=int(input('\nenter bigger range limit'))
                if a>rangeCheck1 and a<rangeCheck2:
                    print('number is within range')
                else:
                    print('number is not within range')

            if c=='n':
                guess=int(input("\nenter guess - "))
                if guess == a:
                    predictionCheck = True
                    correctEntry = True
                    print('\ncorrect guess...')
                else:
                    predictionCheck = False
                    correctEntry = False
                    print('\nincorrect guess...')
        else:
           print('enter valid character\n')
           correctEntry=False
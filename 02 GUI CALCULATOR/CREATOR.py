import tkinter
from tkinter.constants import COMMAND
number = [0,0]
i=0
operator=''

def insert_1():
    number[i] = (10*number[i]) + 1          #addition done
    text_1.config(text=number[i])           #display working properly
def insert_2():
    number[i] = (10*number[i]) + 2          #addition done
    text_1.config(text=number[i])           #display working properly
def insert_3():
    number[i] = (10*number[i]) + 3          #addition done
    text_1.config(text=number[i])           #display working properly
def add_num():
    text_1.config(text="+")
    global i,operator
    i=i+1
    operator='+'
    if i>1:
        label_1_f2.config(text="variable limit has been reached. warning!!!\npress '=' for result")
        i=1
def insert_4():
    number[i] = (10*number[i]) + 4          #addition done
    text_1.config(text=number[i])           #display working properly
def insert_5():
    number[i] = (10*number[i]) + 5          #addition done
    text_1.config(text=number[i])           #display working properly
def insert_6():
    number[i] = (10*number[i]) + 6          #addition done
    text_1.config(text=number[i])           #display working properly
def sub_num():
    text_1.config(text="-")
    global i,operator
    i=i+1
    operator='-'
    if i>1:
        label_1_f2.config(text="variable limit has been reached. warning!!!\npress '=' for result")
        i=1
def insert_7():
    number[i] = (10*number[i]) + 7          #addition done
    text_1.config(text=number[i])           #display working properly
def insert_8():
    number[i] = (10*number[i]) + 8          #addition done
    text_1.config(text=number[i])           #display working properly
def insert_9():
    number[i] = (10*number[i]) + 9          #addition done
    text_1.config(text=number[i])           #display working properly
def mul_num():
    text_1.config(text="x")
    global i,operator
    i=i+1
    operator='*'
    if i>1:
        label_1_f2.config(text="variable limit has been reached. warning!!!\npress '=' for result")
        i=1
def clear():
    global operator,i
    i=0
    number[0]=0
    number[1]=0
    text_1.config(text="0")           #display working properly
    label_1_f2.config(text="MATH CALCULATOR")
    operator=''
def insert_0():
    number[i] = (10*number[i]) + 0          #addition done
    text_1.config(text=number[i])           #display working properly
def result():
    if operator=='+':
        text_1.config(text=number[0]+number[1])
    if operator=='-':
        text_1.config(text=number[0]-number[1])
    if operator=='*':
        text_1.config(text=number[0]*number[1])
    if operator=='/':
        text_1.config(text=float(number[0])/float(number[1]))
def div_num():
    text_1.config(text="/")
    global i,operator
    i=i+1
    operator='/'
    if i>1:
        label_1_f2.config(text="variable limit has been reached. warning!!!\npress '=' for result")
        i=1

root = tkinter.Tk()
root.title("root_window")
root.geometry("385x460")
root.minsize(385,460)
root.maxsize(385,460)

frame_number_display = tkinter.Frame(root)
text_1=tkinter.Label(frame_number_display,width=20,height=1,padx=5,pady=5,text="0")
#NAME OF FRAME IS GIVEN AS A PARAMETER
text_1.grid(padx=10,pady=10)
frame_number_display.grid()

frame_purpose_display = tkinter.Frame(root)
label_1_f2= tkinter.Label(frame_purpose_display,text = "MATH CALCULATOR",width=40,height=2)
label_1_f2.grid()
frame_purpose_display.grid()

frame_3 = tkinter.Frame(root)

button_1 = tkinter.Button(frame_3,text="1",padx=30,pady=25,command=insert_1)     #button ready
button_1.grid(column=0,row=0,padx=10,pady=10)
button_2 = tkinter.Button(frame_3,text="2",padx=30,pady=25,command=insert_2)     #button ready
button_2.grid(column=1,row=0,padx=10,pady=10)
button_3 = tkinter.Button(frame_3,text="3",padx=30,pady=25,command=insert_3)     #button ready
button_3.grid(column=2,row=0,padx=10,pady=10)
add_button = tkinter.Button(frame_3,text="+",padx=30,pady=25,command=add_num)     #button ready
add_button.grid(column=3,row=0,padx=10,pady=10)

button_4 = tkinter.Button(frame_3,text="4",padx=30,pady=25,command=insert_4)     #button ready
button_4.grid(column=0,row=1,padx=10,pady=10)
button_5 = tkinter.Button(frame_3,text="5",padx=30,pady=25,command=insert_5)     #button ready
button_5.grid(column=1,row=1,padx=10,pady=10)
button_6 = tkinter.Button(frame_3,text="6",padx=30,pady=25,command=insert_6)     #button ready
button_6.grid(column=2,row=1,padx=10,pady=10)
sub_button = tkinter.Button(frame_3,text="-",padx=30,pady=25,command=sub_num)     #button ready
sub_button.grid(column=3,row=1,padx=10,pady=10)

button_7 = tkinter.Button(frame_3,text="7",padx=30,pady=25,command=insert_7)     #button ready
button_7.grid(column=0,row=2,padx=10,pady=10)
button_8 = tkinter.Button(frame_3,text="8",padx=30,pady=25,command=insert_8)     #button ready
button_8.grid(column=1,row=2,padx=10,pady=10)
button_9 = tkinter.Button(frame_3,text="9",padx=30,pady=25,command=insert_9)     #button ready
button_9.grid(column=2,row=2,padx=10,pady=10)
mul_button = tkinter.Button(frame_3,text="x",padx=30,pady=25,command=mul_num)     #button ready
mul_button.grid(column=3,row=2,padx=10,pady=10)

button_C = tkinter.Button(frame_3,text="C",padx=30,pady=25,command=clear)     #button ready
button_C.grid(column=0,row=3,padx=10,pady=10)
button_0 = tkinter.Button(frame_3,text="0",padx=30,pady=25,command=insert_0)     #button ready
button_0.grid(column=1,row=3,padx=10,pady=10)
button_result = tkinter.Button(frame_3,text="=",padx=30,pady=25,command=result)     #button ready
button_result.grid(column=2,row=3,padx=10,pady=10)
div_button = tkinter.Button(frame_3,text="/",padx=30,pady=25,command=div_num)     #button ready
div_button.grid(column=3,row=3,padx=10,pady=10)

frame_3.grid()

root.mainloop()
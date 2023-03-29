import tkinter
import backend_for_calc
sales_list=[]
details =""
def list_addition():
    global details
    temp_txt=""
    temp_txt+=entrybox.get(1.0,"end")
    temp_txt=temp_txt.strip()
    sales_list.append(temp_txt)
    entrybox.delete(1.0,"end")
    details+=temp_txt+"\n"
    display_entry.config(text=details)
    print(sales_list,temp_txt)

def confirmation():
    list_addition()
    output="after calculation, the total sales calculated = \n"+backend_for_calc.run_calculation(sales_list)
    display_entry.config(text=output)




root = tkinter.Tk()
root.geometry("600x400")
root.minsize(600,400)
root.maxsize(800,600)
root.title("cost management")

entry_frame= tkinter.Frame(root)
entrybox = tkinter.Text(entry_frame,height=1,width=60)
entrybox.grid(column=0,row=0,padx=4,pady=4)
warning_text = tkinter.Label(entry_frame,text="format = name_of_product price",width=50)
warning_text.grid(row=1,column=0,padx=4,pady=4)
entry_frame.grid(column=0,row=0,padx=50,pady=5)

button_frame = tkinter.Frame(root)
entry_take = tkinter.Button(button_frame,text="add to list",padx=20,pady=1,command=list_addition)
entry_take.grid(column=0,row=2,padx=10,pady=1)
calculate = tkinter.Button(button_frame,text="confirm",padx=20,pady=1,command=confirmation)
calculate.grid(column=1,row=2,padx=10,pady=1)
button_frame.grid(column=0,row=1)

display_frame = tkinter.Frame(root)
display_entry = tkinter.Label(display_frame,text="here, confirmation will be displayed",width=40,height=10)
display_entry.grid()
display_frame.grid(column=0,row=2)

root.mainloop()
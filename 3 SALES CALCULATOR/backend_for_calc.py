def run_calculation(entry_list):
    pro_database={}                         #contains entire details
    pro_name_database=[]                    #only for names
    str_to_return = ""

    for i in range(len(entry_list)):
        pro_cost=0          #current product cost
        pro_name=""         #current product name
        seperated_str=entry_list[i].split(" ")
        
        pro_cost=int(seperated_str[len(seperated_str)-1])

        for j in range(len(seperated_str)-1):
            pro_name+=seperated_str[j]+" "
        pro_name=pro_name.strip()
        if pro_name in pro_database:
            pro_database[pro_name]+=pro_cost
        else:
            pro_database[pro_name]=pro_cost
            pro_name_database.append(pro_name)
    for i in range(len(pro_name_database)):
        str_to_return+=(pro_name_database[i]+" "+str(pro_database[pro_name_database[i]])+"\n")
    return str_to_return
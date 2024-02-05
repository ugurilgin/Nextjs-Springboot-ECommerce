export const addChart =(product)=>{
    return{
        type:"ADDITEM",
        payload:product
    }

}

export const delChart =(product)=>{
    return{
        type:"DELITEM",
        payload:product
    }

}